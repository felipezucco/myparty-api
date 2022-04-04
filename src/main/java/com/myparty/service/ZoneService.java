package com.myparty.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.dto.ZoneDTO;
import com.myparty.model.Zone;
import com.myparty.repository.ZoneRepository;


@Service
public class ZoneService {

	@Autowired
	private ZoneRepository zoneRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public ZoneDTO saveZone(ZoneDTO zoneDTO) {
		Zone zone = mapper.map(zoneDTO, Zone.class);
		zoneRepository.save(zone);
		zoneDTO.setId(zone.getId());
		return zoneDTO;
	}
	
	public List<ZoneDTO> getZones() {
		List<Zone> zones = zoneRepository.findAll();
		return zones.stream().map(l -> mapper.map(l, ZoneDTO.class)).collect(Collectors.toList());
	}
	
	public ZoneDTO getZoneById(Long id) {
		Optional<Zone> zone = zoneRepository.findById(id);		
		return zone.map(l -> mapper.map(l, ZoneDTO.class)).orElseGet(null); 
	}
	
	public void deleteZone(Long id) {
		zoneRepository.deleteById(id);
	}
}
