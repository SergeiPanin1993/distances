package com.example.citiesdistance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceDTOForJson {
    private String CalculationType;
    private List<CityDTO> cityFrom;
    private List<CityDTO> cityTo;

    public String getCalculationType() {
        return CalculationType;
    }


    public void setCalculationType(String calculationType) {
        CalculationType = calculationType;
    }

    public List<CityDTO> getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(List<CityDTO> cityFrom) {
        this.cityFrom = cityFrom;
    }

    public List<CityDTO> getCityTo() {
        return cityTo;
    }

    public void setCityTo(List<CityDTO> cityTo) {
        this.cityTo = cityTo;
    }
}
