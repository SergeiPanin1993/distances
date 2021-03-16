package com.example.citiesdistance.repository;

import com.example.citiesdistance.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

    public City findByName(String name);
}
