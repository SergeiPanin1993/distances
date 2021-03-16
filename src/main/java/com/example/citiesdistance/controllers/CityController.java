package com.example.citiesdistance.controllers;

import com.example.citiesdistance.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public Map<Integer, String> getAllCitiesMap(){
         return cityService.getAllCitiesIdAndNames();
    }
}




















