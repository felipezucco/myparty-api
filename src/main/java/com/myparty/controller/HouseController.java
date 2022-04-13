package com.myparty.controller;

import com.myparty.controller.middleware.HouseMiddleware;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myparty.dto.HouseDTO;

@RestController
@RequestMapping("/api/house")
public class HouseController {

    @Autowired
    private HouseMiddleware middleware;

    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getHousesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(middleware.getHousesById(id));
    }

    @GetMapping
    public ResponseEntity<List<HouseDTO>> getHouses() throws Exception {
        return ResponseEntity.ok(middleware.getHouses());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void persistHouse(@RequestBody HouseDTO houseDTO) throws Exception {
        middleware.persistHouse(houseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteHouse(@PathVariable("id") Long id) throws Exception {
        middleware.deleteHouse(id);
    }

}
