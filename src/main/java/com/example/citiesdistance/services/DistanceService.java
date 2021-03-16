package com.example.citiesdistance.services;

import com.example.citiesdistance.model.City;
import com.example.citiesdistance.model.Distance;

public interface DistanceService {

    Distance findDistanceInDB(City cityFrom, City cityTo);

    void saveDistanceInDB(Distance distance);
}
