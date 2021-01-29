package com.meli.fuegoquasar.models;

import java.util.List;

public class MessageSplitReq {

    private double distance;
    private List<String> message;

    public MessageSplitReq(){

    }

    public MessageSplitReq(double distance, List<String> message) {
        this.distance = distance;
        this.message = message;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

}
