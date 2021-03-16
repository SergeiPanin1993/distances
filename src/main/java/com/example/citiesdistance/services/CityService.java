package com.example.citiesdistance.services;

import com.example.citiesdistance.model.City;

import java.util.Map;

public interface CityService {

    Map<Integer, String> getAllCitiesIdAndNames();

    City findCityInDBByName(String name);

    void saveCityInDB(City city);
}
