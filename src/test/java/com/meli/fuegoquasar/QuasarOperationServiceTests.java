package com.meli.fuegoquasar;

import com.meli.fuegoquasar.exceptions.InvalidSatellitesException;
import com.meli.fuegoquasar.models.Satellite;
import com.meli.fuegoquasar.services.QuasarOperationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuasarOperationServiceTests {

    @Autowired
    private QuasarOperationService quasarOperationService;

    @Test(expected = InvalidSatellitesException.class)
    public void satellitesSizeNotEqualTo3(){
        Satellite satellite1 = new Satellite("kenobi", 100, Arrays.asList("este", "", "", "mensaje", ""));
        Satellite satellite2 = new Satellite("skywalker", 115.5, Arrays.asList("", "es", "", "",  "secreto"));
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2);
        quasarOperationService.getTopSecretResponse(satelliteList);
    }

}
