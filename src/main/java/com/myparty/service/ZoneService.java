package com.myparty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myparty.model.house.Zone;
import com.myparty.repository.ZoneRepository;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;
    
    public Zone saveZone(Zone zone) {
        zoneRepository.save(zone);
        return zone;
    }

    public List<Zone> getZones() {
        return zoneRepository.findAll();
    }

    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id).orElseGet(null);
    }

    public void deleteZone(Long id) {
        zoneRepository.deleteById(id);
    }
}
