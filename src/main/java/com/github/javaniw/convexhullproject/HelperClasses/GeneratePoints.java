package com.github.javaniw.convexhullproject.HelperClasses;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GeneratePoints {

//    formatter to limit the precision to two decimal places
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * generates a list of points with randomly generated x and y coordinates between a specified range
     *
     * @param numOfPoints the number of points to generate
     * @param min the minimal value that the x and y coordinate of the point can be
     * @param max the maximum value that the x and y coordinate of the point can be
     * @return a list of randomly generated points (x coordinate, y coordinate)
     */
    public static List<Double []> generate(int numOfPoints, int min, int max) {
//        a list that will store the generated points
        List<Double []> listOfPoints = new ArrayList<>();
//        loop that runs once for each point that needs to be generated
        for (int i = 0; i < numOfPoints; i++) {
//            randomly generates two double values between the min and max range and limits to two decimal places
            Double x = Double.valueOf(df.format(Math.random() * (max - min + 1) + min));
            Double y = Double.valueOf(df.format(Math.random() * (max - min + 1) + min));

//            creates a new point using these random values and adds the new point to the list
            listOfPoints.add(new Double[]{x, y});
        }
//        returns list
        return listOfPoints;
    }
}
