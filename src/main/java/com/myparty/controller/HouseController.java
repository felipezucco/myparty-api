package com.myparty.controller;

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
import com.myparty.service.HouseService;


@RestController
@RequestMapping("/api/house")
public class HouseController {

	@Autowired
	private HouseService houseService;

	@GetMapping
	private ResponseEntity<List<HouseDTO>> getHousesById(@RequestParam Long[] id) throws Exception {	
		return ResponseEntity.ok(houseService.getHouses(id));			
	}
	
	@GetMapping("/all")
	private ResponseEntity<List<HouseDTO>> getHouses() throws Exception {	
		return ResponseEntity.ok(houseService.getHouses());			
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	private ResponseEntity<HouseDTO> persistHouse(@RequestBody HouseDTO houseDTO) throws Exception {		
		houseService.persistHouse(houseDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(houseDTO);			
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	private void deleteHouse(@PathVariable("id") Long id) throws Exception {
		houseService.deleteHouse(id);			
	}
	
}
