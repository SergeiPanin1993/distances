package com.example.citiesdistance.controllers;

import com.example.citiesdistance.dto.DistanceDTOForJson;
import com.example.citiesdistance.dto.DistanceDTOInputXML;
import com.example.citiesdistance.exception.WrongDataException;
import com.example.citiesdistance.model.DistanceBuilder;
import com.example.citiesdistance.model.DistanceCalculationResult;
import com.example.citiesdistance.model.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DistanceController {

    @Autowired
    DistanceCalculator distanceCalculator;

    @Autowired
    DistanceBuilder distanceBuilder;

    @GetMapping("/distances")
    public List<DistanceCalculationResult> getAllDistances(@RequestBody DistanceDTOForJson distanceDTOforJson){
        try {
            return distanceCalculator.calculateAllDistances(distanceDTOforJson);
        } catch (WrongDataException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/uploadXML", consumes={"application/xml"})
    public ResponseEntity<String> uploadDistancesAndCities(@RequestBody DistanceDTOInputXML distances){
        if(!distanceBuilder.saveAllDistancesAndCitiesInDB(distances)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
