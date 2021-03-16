package com.example.citiesdistance.dto;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"cityFrom", "cityTo", "distance"})
public class DistanceDTO {
    private CityDTO cityFrom;
    private CityDTO cityTo;
    private double distance;

    public DistanceDTO(CityDTO cityFrom, CityDTO cityTo, double distance) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public DistanceDTO() {
    }

    public DistanceDTO(double distance) {
        this.distance = distance;
    }
    public CityDTO getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(CityDTO cityFrom) {
        this.cityFrom = cityFrom;
    }

    public CityDTO getCityTo() {
        return cityTo;
    }

    public void setCityTo(CityDTO cityTo) {
        this.cityTo = cityTo;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
