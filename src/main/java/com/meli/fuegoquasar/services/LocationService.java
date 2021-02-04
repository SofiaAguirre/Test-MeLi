package com.meli.fuegoquasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.fuegoquasar.exceptions.InvalidSatellitesException;
import com.meli.fuegoquasar.models.Position;
import com.meli.fuegoquasar.models.Satellite;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Value("${kenobi.coordenadas}")
    private double[] KENOBI_COORDENADAS;

    @Value("${skywalker.coordenadas}")
    private double[] SKYWALKER_COORDENADAS;

    @Value("${sato.coordenadas}")
    private double[] SATO_COORDENADAS;

    public double[] getLocation(double[][] positions, double[] distances) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        return  solver.solve().getPoint().toArray();
    }

    public Position getSatellitePosition(List<Satellite>satelliteList) {
        double[][] positions = new double[][] {KENOBI_COORDENADAS, SKYWALKER_COORDENADAS, SATO_COORDENADAS } ;
        Optional<Satellite> kenobiOptional = satelliteList.stream().filter(s -> s.getName().equals("kenobi")).findFirst();
        Optional<Satellite> skywalkerOptional = satelliteList.stream().filter(s -> s.getName().equals("skywalker")).findFirst();
        Optional<Satellite> satoOptional = satelliteList.stream().filter(s -> s.getName().equals("sato")).findFirst();
        if (kenobiOptional.isPresent() && skywalkerOptional.isPresent() && satoOptional.isPresent()) {
            Satellite kenobi = kenobiOptional.get();
            Satellite skywalker = skywalkerOptional.get();
            Satellite sato = satoOptional.get();
            double[] distances = new double[]{kenobi.getDistance(), skywalker.getDistance(), sato.getDistance()};
            double[] positionArr = getLocation(positions, distances);
            double positionX = positionArr[0];
            double positionY = positionArr[1];
            return new Position(positionX, positionY);
        } else {
            throw new InvalidSatellitesException("All required satellites are not present on request");
        }
    }

}
