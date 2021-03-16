package com.example.citiesdistance.model;

import com.example.citiesdistance.dto.DistanceDTOForJson;
import com.example.citiesdistance.exception.WrongDataException;

import java.util.List;

public interface DistanceCalculator {
    List<DistanceCalculationResult> calculateAllDistances(DistanceDTOForJson distanceDTOforJson) throws WrongDataException;
}
