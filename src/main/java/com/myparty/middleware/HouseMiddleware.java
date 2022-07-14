package com.myparty.middleware;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.myparty.dto.house.GetHouse;
import com.myparty.model.house.House;
import com.myparty.service.HouseService;
import org.springframework.stereotype.Service;

@Service
public class HouseMiddleware extends RootMiddleware {

    @Autowired
    private HouseService houseService;

    public GetHouse getHousesById(Long id) {
        return convert(houseService.getHouseById(id));
    }

    public List<GetHouse> getHouses() throws Exception {
        return convert(houseService.getHouses());
    }

    public GetHouse persistHouse(GetHouse getHouse) throws Exception {
        House house = convert(getHouse);
        houseService.persistHouse(house);
        return convert(house);
    }

    public void deleteHouse(Long id) throws Exception {
        houseService.deleteHouse(id);
    }

    public List<GetHouse> getHousesByOrganizationId(Long id) {
        return convert(houseService.getHousesByOrganizationId(id));
    }
    
}
