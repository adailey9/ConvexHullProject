package com.github.javaniw.convexhullproject.HelperClasses;

import java.util.List;

public class PrintPoints {
    /**
     * prints points of list in "(x, y)" format
     *
     * @param listOfPoints a list of points
     */
    public static void print(List<Double []> listOfPoints) {
//        loops through list of points and print "(x coordinate, y coordinate)"
        for (Double [] point : listOfPoints) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }
    }
}
