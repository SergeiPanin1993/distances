package com.example.citiesdistance.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;

@JsonAutoDetect
@Entity
@Table (name = "distance")
public class Distance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "from_city")
    private City cityFrom;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "to_city")
    private City cityTo;

    @Column(name = "distance", columnDefinition = "numeric")
    private double distanceBetweenCities;

    public Distance() {
    }

    public Distance(City cityFrom, City cityTo, double distanceBetweenCities) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distanceBetweenCities = distanceBetweenCities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public double getDistanceBetweenCities() {
        return distanceBetweenCities;
    }

    public void setDistanceBetweenCities(double distanceBetweenCities) {
        this.distanceBetweenCities = distanceBetweenCities;
    }
}
