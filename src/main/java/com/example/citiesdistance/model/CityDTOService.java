package com.example.citiesdistance.model;

import com.example.citiesdistance.dto.CityDTO;
import com.example.citiesdistance.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
@Component
public class CityDTOService {

    @Autowired
    CityService cityService;

    public City getNewCityByDTO(CityDTO cityDTO){
        City city = cityService.findCityInDBByName(cityDTO.getName());
        if(city == null) {
            return new City(cityDTO.getName(), cityDTO.getLatitude(), cityDTO.getLongitude());
        }
        return city;
    }


    public List<City> getNewCitiesByDTO(List<CityDTO> citiesDTO){
        List<City> cities = new LinkedList<>();
        for(CityDTO cityDTO : citiesDTO){
            cities.add(getNewCityByDTO(cityDTO));
        }
        return cities;
    }
}
