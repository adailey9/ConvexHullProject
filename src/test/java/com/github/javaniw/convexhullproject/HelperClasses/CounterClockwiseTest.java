package com.github.javaniw.convexhullproject.HelperClasses;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CounterClockwiseTest {

    List<Double []> points = new ArrayList<>(Arrays.asList(new Double[]{-51.6, 72.97}
            , new Double[]{33.58, 98.97}, new Double[]{-86.68, 9.77}, new Double[]{-49.41, -46.26}));

    @Test
    void calculateCenterPoint() {
        Double [] centerPoint = CounterClockwise.calculateCenterPoint(points);
        System.out.println(Arrays.toString(centerPoint));
    }

    @Test
    void order() {
        List<Double[]> sortedList = CounterClockwise.order(points);
        for (Double [] point : sortedList) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
    }
}