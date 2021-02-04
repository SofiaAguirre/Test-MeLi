package com.meli.fuegoquasar.models;

import java.util.List;

public class MessageReq {

    private List<Satellite> satellites;

    public MessageReq(){

    }

    public MessageReq(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

}
