package com.example.citiesdistance.model;

import com.example.citiesdistance.dto.DistanceDTO;
import com.example.citiesdistance.dto.DistanceDTOInputXML;
import com.example.citiesdistance.services.CityService;
import com.example.citiesdistance.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DistanceBuilder {

    @Autowired
    DistanceService distanceService;

    @Autowired
    CityService cityService;

    @Autowired
    CityDTOService cityDTOService;

    public boolean saveAllDistancesAndCitiesInDB(DistanceDTOInputXML distanceDTOInputXML){

        for(DistanceDTO distanceDTO : distanceDTOInputXML.getAllDistances()){

            City cityFrom = cityService.findCityInDBByName(distanceDTO.getCityFrom().getName());
            if(cityFrom == null){
                cityFrom = cityDTOService.getNewCityByDTO(distanceDTO.getCityFrom());
                cityService.saveCityInDB(cityFrom);
            }

            City cityTo = cityService.findCityInDBByName(distanceDTO.getCityTo().getName());
            if(cityTo == null){
                cityTo = cityDTOService.getNewCityByDTO(distanceDTO.getCityTo());
                cityService.saveCityInDB(cityTo);
            }

            Distance distance = distanceService.findDistanceInDB(cityFrom, cityTo);
            if(distance == null){
                distance = createNewDistance(cityFrom, cityTo, distanceDTO.getDistance());
                distanceService.saveDistanceInDB(distance);
            }
        }
        return true;
    }

    private Distance createNewDistance(City cityFrom, City cityTo, double distanceBetweenCities) {
        Distance distance = new Distance(cityFrom, cityTo,distanceBetweenCities);
        return distance;
    }
}
