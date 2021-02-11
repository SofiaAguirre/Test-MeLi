package com.meli.fuegoquasar;

import com.meli.fuegoquasar.exceptions.InvalidMessageException;
import com.meli.fuegoquasar.models.Satellite;
import com.meli.fuegoquasar.services.MessageDecoderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageDecoderServiceTests {

    @Autowired
    private MessageDecoderService messageDecoderService;

    @Test(expected = InvalidMessageException.class)
    public void invalidMessageSize(){
        Satellite satellite1 = new Satellite("kenobi", 100, Arrays.asList("este", "", "", "mensaje", ""));
        Satellite satellite2 = new Satellite("skywalker", 115.5, Arrays.asList("", "es", "", "",  "secreto", ""));
        Satellite satellite3 = new Satellite("sato", 142.7, Arrays.asList("este", "", "un", "", ""));
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2, satellite3);
        messageDecoderService.getMessage(satelliteList);
    }

    @Test
    public void validDecodedMessage1(){
        Satellite satellite1 = new Satellite("kenobi", 100, Arrays.asList("este", "", "", "mensaje", ""));
        Satellite satellite2 = new Satellite("skywalker", 115.5, Arrays.asList("", "es", "", "",  "secreto"));
        Satellite satellite3 = new Satellite("sato", 142.7, Arrays.asList("este", "", "un", "", ""));
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2, satellite3);
        String expectedMessage = "este es un mensaje secreto";
        String decodedMessage = messageDecoderService.getMessage(satelliteList);
        Assert.assertEquals(expectedMessage, decodedMessage);
    }

    @Test
    public void validDecodedMessage2(){
        Satellite satellite1 = new Satellite("kenobi", 100, Arrays.asList("", "nos", "", "", ""));
        Satellite satellite2 = new Satellite("skywalker", 115.5, Arrays.asList("", "nos", "", "",  "atacando"));
        Satellite satellite3 = new Satellite("sato", 142.7, Arrays.asList("ayuda", "", "estan", "", ""));
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2, satellite3);
        String expectedMessage = "ayuda nos estan atacando";
        String decodedMessage = messageDecoderService.getMessage(satelliteList);
        Assert.assertEquals(expectedMessage, decodedMessage);
    }

    @Test
    public void validDecodedMessage3(){
        Satellite satellite1 = new Satellite("kenobi", 100, Arrays.asList("", "fuerzas", "", "avanzan", "", "", "oeste"));
        Satellite satellite2 = new Satellite("skywalker", 115.5, Arrays.asList("las", "", "enemigas", "",  "", "", ""));
        Satellite satellite3 = new Satellite("sato", 142.7, Arrays.asList("", "fuerzas", "", "", "hacia", "el", ""));
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2, satellite3);
        String expectedMessage = "las fuerzas enemigas avanzan hacia el oeste";
        String decodedMessage = messageDecoderService.getMessage(satelliteList);
        Assert.assertEquals(expectedMessage, decodedMessage);
    }

}
