package com.github.javaniw.convexhullproject.BruteForceAlgorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BruteForceConvexHullTest {

    @Test
    void findConvexHull() {
        /**
         * Test Points:
         * (1, 1)
         * (1, 6)
         * (2, 2)
         * (2, 4)
         * (3, 2)
         * (3, 3)
         * (3, 5)
         * (3, 7)
         * (4, 4)
         * (4, 6)
         */

        List<Double []> points = new ArrayList<>();
        points.add(new Double[]{1.0, 1.0});
        points.add(new Double[]{1.0, 6.0});
        points.add(new Double[]{2.0, 2.0});
        points.add(new Double[]{2.0, 4.0});
        points.add(new Double[]{3.0, 2.0});
        points.add(new Double[]{3.0, 3.0});
        points.add(new Double[]{3.0, 5.0});
        points.add(new Double[]{3.0, 7.0});
        points.add(new Double[]{4.0, 4.0});
        points.add(new Double[]{4.0, 6.0});

        List<Double[]> convexHullPoints = BruteForceConvexHull.findConvexHull(points);
        for (Double [] point : convexHullPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
    }
}