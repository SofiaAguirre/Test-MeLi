package com.meli.fuegoquasar.models;

import java.util.List;

public class Satellite {

    private String name;
    private double distance;
    private List<String> message;

    public Satellite(){

    }

    public Satellite(String name, double distance, List<String> message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    public List<String> getMessage() {
        return message;
    }

}
