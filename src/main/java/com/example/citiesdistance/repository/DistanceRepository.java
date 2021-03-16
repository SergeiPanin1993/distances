package com.example.citiesdistance.repository;

import com.example.citiesdistance.model.City;
import com.example.citiesdistance.model.Distance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DistanceRepository extends JpaRepository<Distance, Integer> {


    Distance findByCityFromAndCityTo(City cityFrom, City cityTo);
}
