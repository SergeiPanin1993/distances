package com.example.citiesdistance.services;

import com.example.citiesdistance.model.City;
import com.example.citiesdistance.model.Distance;
import com.example.citiesdistance.repository.DistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService{

    @Autowired
    DistanceRepository distanceRepository;

    @Override
    public Distance findDistanceInDB(City cityFrom, City cityTo) {
        return distanceRepository.findByCityFromAndCityTo(cityFrom, cityTo);
    }

    @Override
    public void saveDistanceInDB(Distance distance) {
        distanceRepository.saveAndFlush(distance);
    }
}
