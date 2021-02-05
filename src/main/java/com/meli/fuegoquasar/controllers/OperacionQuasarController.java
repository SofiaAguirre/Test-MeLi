package com.meli.fuegoquasar.controllers;

import com.meli.fuegoquasar.exceptions.InvalidSatellitesException;
import com.meli.fuegoquasar.models.*;
import com.meli.fuegoquasar.services.LocationService;
import com.meli.fuegoquasar.services.MessageDecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE)
public class OperacionQuasarController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MessageDecoderService messageDecoderService;

    @PostMapping("/topsecret")
    public ResponseEntity postAllSatellitesMessages(@RequestBody MessageReq messageReq){
        Position position;
        String message;
        List<Satellite> satelliteList = messageReq.getSatellites();
        if(satelliteList.size() == 3) {
            position = locationService.getSatellitePosition(satelliteList);
            message = messageDecoderService.getMessage(satelliteList);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageRes(position, message));
        } else {
            throw new InvalidSatellitesException("Quantity of satellites (" + satelliteList.size() + ") is different than the required.");
        }
    }

    @PostMapping("/topsecret_split/{satellite}")
    public void postSatelliteMessage(@PathVariable String satellite, @RequestBody MessageSplitReq messageSplitReq){
        //Retorna 200 si el formato es correcto, caso contrario excepcion BAD REQUEST
    }

    @GetMapping("/topsecret_split")
    public MessageRes getTopSecretMessage(){
        //Implementar Cache para guardar registros de mensajes
        //Cada vez que haya un mensaje (incompleto) de cada uno de los satelites y el mismo sea consumido(completo) se restablecerá la caché, dejandola limpia para nuevos mensajes
        return new MessageRes(); //Se retorna el mensaje entero junto con la posicion (de ser posible), sino excepcion con BAD REQUEST
    }
}
