package com.meli.fuegoquasar.exceptions;

import org.springframework.web.client.RestClientException;

public class UnknownSatelliteException extends RestClientException {
    public UnknownSatelliteException(String msg) {
        super(msg);
    }
}
