package com.meli.fuegoquasar.services;

import com.meli.fuegoquasar.exceptions.InvalidSatellitesException;
import com.meli.fuegoquasar.models.MessageRes;
import com.meli.fuegoquasar.models.Position;
import com.meli.fuegoquasar.models.Satellite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuasarOperationService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private MessageDecoderService messageDecoderService;

    public ResponseEntity getTopSecretResponse(List<Satellite> satelliteList) {
        Position position;
        String message;
        if(satelliteList.size() == 3) {
            position = locationService.getSatellitePosition(satelliteList);
            message = messageDecoderService.getMessage(satelliteList);
            return ResponseEntity.status(HttpStatus.OK).body(new MessageRes(position, message));
        } else {
            throw new InvalidSatellitesException("ERROR: Missing information. Quantity of satellites (" + satelliteList.size() + ") is different than the required.");
        }
    }

}
