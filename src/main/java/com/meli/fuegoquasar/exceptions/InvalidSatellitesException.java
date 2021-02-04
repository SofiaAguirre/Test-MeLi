package com.meli.fuegoquasar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidSatellitesException extends RestClientException {
    public InvalidSatellitesException(String msg) {
            super(msg);
        }
}
