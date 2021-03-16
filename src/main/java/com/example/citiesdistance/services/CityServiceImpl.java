package com.example.citiesdistance.services;

import com.example.citiesdistance.model.City;
import com.example.citiesdistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Map<Integer, String> getAllCitiesIdAndNames() {
        List<City> allCities = cityRepository.findAll();
        Map<Integer, String> allCitiesIdAndNames = new TreeMap<>();
        for(City city : allCities){
            allCitiesIdAndNames.put(city.getId(), city.getName());
        }
        return allCitiesIdAndNames;
    }

    @Override
    public City findCityInDBByName(String name) {
        City city = cityRepository.findByName(name);
        return city;
    }

    @Override
    public void saveCityInDB(City city) {
        if(cityRepository.findByName(city.getName()) == null)
        cityRepository.saveAndFlush(city);
    }
}
