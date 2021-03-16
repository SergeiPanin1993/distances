package com.example.citiesdistance.model;

import com.example.citiesdistance.dto.DistanceDTOForJson;
import com.example.citiesdistance.exception.WrongDataException;
import com.example.citiesdistance.services.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DistanceCalculatorImpl implements DistanceCalculator{

    @Autowired
    DistanceService distanceService;

    @Autowired
    CityDTOService cityDTOService;

    @Override
    public List<DistanceCalculationResult> calculateAllDistances(DistanceDTOForJson distanceDTOforJson) throws WrongDataException {

        List<City> cityFrom = cityDTOService.getNewCitiesByDTO(distanceDTOforJson.getCityFrom());
        List<City> cityTo = cityDTOService.getNewCitiesByDTO(distanceDTOforJson.getCityTo());
        if(distanceDTOforJson.getCalculationType().equalsIgnoreCase("Distancematrix")){
            return getCalculationResultFromDB(cityFrom, cityTo);
        }
        if(distanceDTOforJson.getCalculationType().equalsIgnoreCase("Crowflight")){
            return getCalculationResultFromDistanceCalculator(cityFrom, cityTo);
        }
        if(distanceDTOforJson.getCalculationType().equalsIgnoreCase("All")){
            return getCalculationAll(cityFrom, cityTo);
        }
        throw new WrongDataException("Wrong Calculation Type");
    }


    private List<DistanceCalculationResult> getCalculationResultFromDB(List<City> cityFrom, List<City> cityTo) throws WrongDataException {
        List<DistanceCalculationResult> list = new LinkedList<>();
        for(int i = 0; i<cityFrom.size(); i++){
            for(int j = 0; j<cityTo.size(); j++){
                DistanceCalculationResult result =
                        getDistanceBetweenTwoCitiesFromDB(cityFrom.get(i), cityTo.get(j));
                if(result!=null){
                    list.add(result);
                }
            }
        }
        return list;
    }


    private List<DistanceCalculationResult> getCalculationResultFromDistanceCalculator(List<City> citiesFrom, List<City> citiesTo){

        List<DistanceCalculationResult> list = new ArrayList<>();
        for(int i = 0; i<citiesFrom.size(); i++){
            for(int j = 0; j<citiesTo.size(); j++){
                double distance = calculateDistanceCrowflightBetweenTwoCities(citiesFrom.get(i), citiesTo.get(j));
                if(distance!=0){
                    list.add(new DistanceCalculationResult(citiesFrom.get(i).getName(), citiesTo.get(j).getName(), distance));
                }
            }
        }
        return list;
    }


    private List<DistanceCalculationResult> getCalculationAll(List<City> citiesFrom, List<City> citiesTo) throws WrongDataException {
        List<DistanceCalculationResult> list = new LinkedList<>();
        for(int i = 0; i<citiesFrom.size(); i++){
            for(int j = 0; j<citiesTo.size(); j++){
                DistanceCalculationResult result = getDistanceBetweenTwoCitiesFromDB(citiesFrom.get(i), citiesTo.get(i));
                if(result == null){
                    result = getDistanceBetweenTwoCitiesCrowflight(citiesFrom.get(i), citiesTo.get(i));
                }
                if(result!=null){
                    list.add(result);
                }
            }
        }
        return list;
    }


    private DistanceCalculationResult getDistanceBetweenTwoCitiesCrowflight(City cityFrom, City cityTo) throws WrongDataException {
        if(cityFrom.equals(cityTo)){
            throw new WrongDataException("Same city");
        }
        return new DistanceCalculationResult(cityFrom.getName(), cityTo.getName(),
                calculateDistanceCrowflightBetweenTwoCities(cityFrom, cityTo));
    }


    private double calculateDistanceCrowflightBetweenTwoCities(City cityFrom, City cityTo){
        double localVar = 3.1416/180;
        double result = 6371 * Math.acos(Math.sin(cityFrom.getLatitude() * localVar)
                *Math.sin(cityTo.getLatitude() * localVar) +
                Math.cos(cityFrom.getLatitude() * localVar)*Math.cos(cityTo.getLatitude() * localVar)
                        * Math.cos(cityFrom.getLongitude() * localVar - cityTo.getLongitude() * localVar));
        return result;
    }

    private DistanceCalculationResult getDistanceBetweenTwoCitiesFromDB(City cityFrom, City cityTo) throws WrongDataException {
        if(cityFrom.equals(cityTo)){
            throw new WrongDataException("Same city");
        }
        Distance distance;
        distance = distanceService.findDistanceInDB(cityFrom, cityTo);
        if(distance == null){
            distance = distanceService.findDistanceInDB(cityTo, cityFrom);
        }
        if(distance == null){
            return null;
        }
        return new DistanceCalculationResult(distance.getCityFrom().getName(),
                distance.getCityTo().getName(), distance.getDistanceBetweenCities());
    }
}
