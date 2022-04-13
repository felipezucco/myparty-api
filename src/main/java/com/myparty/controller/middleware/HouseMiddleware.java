package com.myparty.controller.middleware;

import com.myparty.controller.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myparty.dto.HouseDTO;
import com.myparty.model.House;
import com.myparty.service.HouseService;
import org.springframework.stereotype.Service;

@Service
public class HouseMiddleware extends RootController {

    @Autowired
    private HouseService houseService;

    public HouseDTO getHousesById(Long id) {
        return data.convert(houseService.getHouseById(id));
    }

    public List<HouseDTO> getHouses() throws Exception {
        return data.convert(houseService.getHouses());
    }

    public HouseDTO persistHouse(HouseDTO houseDTO) throws Exception {
        House house = data.convert(houseDTO);
        houseService.persistHouse(house);
        return data.convert(house);
    }

    public void deleteHouse(Long id) throws Exception {
        houseService.deleteHouse(id);
    }

}
