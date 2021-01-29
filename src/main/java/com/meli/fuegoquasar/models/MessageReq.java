package com.meli.fuegoquasar.models;

import java.util.List;

public class MessageReq {

    private List<Satellite> satelites;

    public MessageReq(){

    }

    public MessageReq(List<Satellite> satelites) {
        this.satelites = satelites;
    }

    public List<Satellite> getSatelites() {
        return satelites;
    }

}
