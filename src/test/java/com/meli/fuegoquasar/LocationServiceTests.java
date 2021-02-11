package com.meli.fuegoquasar;

import com.meli.fuegoquasar.exceptions.InvalidSatellitesException;
import com.meli.fuegoquasar.models.Satellite;
import com.meli.fuegoquasar.services.LocationService;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LocationServiceTests {

    @Autowired
    private LocationService locationService;

    @Test(expected = InvalidSatellitesException.class)
    public void invalidSatellites(){
        Satellite satellite1 = new Satellite("kenovi", 100, new ArrayList<>());
        Satellite satellite2 = new Satellite("skywalker", 115.5, new ArrayList<>());
        Satellite satellite3 = new Satellite("sato", 142.7, new ArrayList<>());
        List<Satellite> satelliteList = Arrays.asList(satellite1, satellite2, satellite3);
        locationService.getSatellitePosition(satelliteList);
    }

    @Test
    public void trilaterationExact(){
        double[][] positions = new double[][]{{1.0, 1.0}, {1.0, 1.0}, {1.0, 1.0}};
        double[] distances = new double[]{1.0, 1.0, 1.0};
        double[] expectedPosition = new double[]{1.0, 1.0};
        double[] finalPosition = locationService.getLocation(positions, distances);
        assertArrayEquals(expectedPosition, finalPosition, 0.0);
    }

    @Test
    public void trilaterationInexact(){
        double[][] positions = new double[][]{{1.0, 1.0}, {3.0, 1.0}, {2.0, 2.0}};
        double[] distances = new double[]{0.9, 1.0, 1.0};
        double[] expectedPosition = new double[]{2.0, 1.0};
        double[] finalPosition = locationService.getLocation(positions, distances);
        Assert.assertThat(expectedPosition, IsNot.not(IsEqual.equalTo(finalPosition)));
    }

}
