package com.myparty.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.HouseDTO;
import com.myparty.exception.HouseRuntimeException;
import com.myparty.model.House;
import com.myparty.repository.HouseRepository;


@Service
public class HouseService {

	@Autowired
	private HouseRepository houseRepository;

	@Autowired
	private LocalService localService;

	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private ModelMapper mapper;

	public HouseDTO persistHouse(HouseDTO houseDTO) {
		localService.saveLocal(houseDTO.getLocal());
		House house = mapper.map(houseDTO, House.class);
		houseRepository.save(house);
		houseDTO.setId(house.getId());
		houseDTO.getZones().stream().forEach(zone -> {
			zone.setHouseId(houseDTO.getId());
			zoneService.saveZone(zone);
		});	
		return houseDTO;
	}

	public List<HouseDTO> getHouses(Long... ids) {
		List<House> houses = null;
		
		if (ids.length > 0) {
			houses = houseRepository.findAllById(Arrays.asList(ids));
		} else {
			houses = houseRepository.findAll();			
		}
		
		return houses.stream().map(h -> mapper.map(h, HouseDTO.class)).collect(Collectors.toList());
	}
	
	public void deleteHouse(Long id) {
		Optional<House> optHouse = houseRepository.findById(id);

		// Verify house id
		if (optHouse.isEmpty()) 
			throw new HouseRuntimeException("Casa de evento não existe.");
		
		List<Long> zonesId = optHouse.get().getZones().stream().map(zone -> zone.getId()).collect(Collectors.toList());		
		zonesId.stream().forEach(zoneId -> zoneService.deleteZone(zoneId));		
		houseRepository.deleteById(id);
	}

}
