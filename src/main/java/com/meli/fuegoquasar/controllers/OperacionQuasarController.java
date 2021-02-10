package com.meli.fuegoquasar.controllers;

import com.meli.fuegoquasar.cache.CacheConfiguration;
import com.meli.fuegoquasar.exceptions.BadRequestException;
import com.meli.fuegoquasar.exceptions.UnknownSatelliteException;
import com.meli.fuegoquasar.models.MessageReq;
import com.meli.fuegoquasar.models.MessageRes;
import com.meli.fuegoquasar.models.MessageSplitReq;
import com.meli.fuegoquasar.models.Satellite;
import com.meli.fuegoquasar.services.QuasarOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping(value = "/api")
public class OperacionQuasarController {

    @Autowired
    private QuasarOperationService quasarOperationService;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @PostMapping("/topsecret")
    @ApiOperation(value = "Nivel 2: Servicio que recibe información de los Satelites de la Operacion Quasar")
    public ResponseEntity<MessageRes> postAllSatellitesMessages(@RequestBody MessageReq messageReq){
        List<Satellite> satelliteList = messageReq.getSatellites();
        try {
            return quasarOperationService.getTopSecretResponse(satelliteList);
        } catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/topsecret_split/{satelliteName}")
    @ApiOperation(value = "Nivel 3: Servicio que recibe información de un Satelite determinado")
    public void postSatelliteMessage(@PathVariable String satelliteName, @RequestBody MessageSplitReq messageSplitReq){
        ConcurrentMap<String, Satellite> satellitesMap = cacheConfiguration.satelliteCache.asMap();
        if(satelliteName.equals("kenobi") || satelliteName.equals("skywalker") || satelliteName.equals("sato")) {
            if(!satellitesMap.containsKey(satelliteName)) {
                double distance = messageSplitReq.getDistance();
                List<String> messageList = messageSplitReq.getMessage();
                Satellite satellite = new Satellite(satelliteName, distance, messageList);
                cacheConfiguration.satelliteCache.put(satelliteName, satellite);
            } else {
                throw new BadRequestException("ERROR: A message from Satellite " + satelliteName + " already exists on the loop.");
            }
        } else {
            throw new UnknownSatelliteException("ERROR: Permission denied to satellite " + satelliteName);
        }
    }

    @GetMapping("/topsecret_split")
    @ApiOperation(value = "Nivel 3: Servicio que retorna fuente y contenido del mensaje de auxilio")
    public ResponseEntity<MessageRes> getTopSecretMessage(){
        ConcurrentMap<String, Satellite> satellitesMap = cacheConfiguration.satelliteCache.asMap();
        List<Satellite> satelliteList = new ArrayList<>(satellitesMap.values());
        ResponseEntity<MessageRes> responseEntity;
        try {
            responseEntity = quasarOperationService.getTopSecretResponse(satelliteList);
            cacheConfiguration.satelliteCache.invalidateAll();
        } catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
        return responseEntity;
    }

}
