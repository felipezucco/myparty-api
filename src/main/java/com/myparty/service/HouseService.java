package com.myparty.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.exception.NoValueFoundException;
import com.myparty.model.House;
import com.myparty.repository.HouseRepository;

@Service
public class HouseService extends RootService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private LocalService localService;

    @Autowired
    private ZoneService zoneService;

    public void persistHouse(House house) {
        localService.persistLocal(house.getLocal());
        houseRepository.save(house);
        house.getZones().stream().forEach(zone -> {
            zone.setHouse(house);
            zoneService.saveZone(zone);
        });
    }

    public List<House> getHouses() {
        return houseRepository.findAll();   
    }
    
    public House getHouseById(Long id) {
        return houseRepository.findById(id).orElseThrow(NoValueFoundException::new);   
    }

    public void deleteHouse(Long id) {
        Optional<House> optHouse = houseRepository.findById(id);

        // Verify house id
        if (!optHouse.isPresent()) {
            throw new NoValueFoundException();
        }

        List<Long> zonesId = optHouse.get().getZones().stream().map(zone -> zone.getId()).collect(Collectors.toList());
        zonesId.stream().forEach(zoneId -> zoneService.deleteZone(zoneId));
        houseRepository.deleteById(id);
    }

}
