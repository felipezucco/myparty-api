package com.myparty.controller;

import com.myparty.dto.house.PersistHouse;
import com.myparty.middleware.HouseMiddleware;
import java.util.List;

import com.myparty.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.house.GetHouse;

@RestController
@RequestMapping("/api/house")
public class HouseController extends ControllerComponent {

    @Autowired
    private HouseService houseService;

    @GetMapping("/{id}")
    public ResponseEntity<GetHouse> getHousesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(houseService.getHouseById(id)));
    }

    @GetMapping
    public ResponseEntity<List<GetHouse>> getHouses() throws Exception {
        return ResponseEntity.ok(_8(houseService.getHouses()));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistHouse(@RequestBody PersistHouse persistHouse) throws Exception {
        houseService.persistHouse(_8(persistHouse));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteHouse(@PathVariable("id") Long id) throws Exception {
        houseService.deleteHouse(id);
    }
    
    @GetMapping("/org/{id}")
    public ResponseEntity<List<GetHouse>> getHousesByOrganizationId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(_8(houseService.getHousesByOrganizationId( id)));
    }

}
