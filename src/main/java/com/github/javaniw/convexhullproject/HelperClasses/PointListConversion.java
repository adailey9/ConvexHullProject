package com.github.javaniw.convexhullproject.HelperClasses;

import com.github.javaniw.convexhullproject.QuickHullAlgorithm.Point;

import java.util.ArrayList;
import java.util.List;

public class PointListConversion {

    /**
     * converts a list of Point objects into a list of points represented as a Double[]
     *
     * @param listOfPoints a list of Point objects
     * @return
     */
    public static List<Double[]> pointListToDoubleList(List<Point> listOfPoints) {
//        a list of type Double[] to store final list
        List<Double[]> setOfPoints = new ArrayList<>();
//        loops through received list parameter and creates a new Double[] for each point
//        by using Point.x for Double[0] and Point.y for Double[1]
        for (Point P : listOfPoints) {
            setOfPoints.add(new Double[]{P.x, P.y});
        }
//        returns final list
        return setOfPoints;
    }

    /**
     * converts a Point object into a point represented as a Double[]
     *
     * @param point a Point object
     * @return
     */
    public static Double[] pointToDouble(Point point) {
//        returns a new Double[] object created by x and y properties of the Point object
        return new Double[]{point.x, point.y};
    }

    /**
     * converts a point represented as a Double[] into a Point object
     *
     * @param pointInArrayFormat a point represented as a Double[]
     * @return
     */
    public static Point doubleToPoint(Double [] pointInArrayFormat) {
//        returns a new Point object created by index 0 and index 1 of the Double[]
        return new Point(pointInArrayFormat[0], pointInArrayFormat[1]);
    }

    /**
     * converts a list of points represented as a Double[] into a list of Point objects
     *
     * @param listOfPoints a list of points represented as a Double[]
     * @return
     */
    public static List<Point> doubleListToPointList(List<Double []> listOfPoints) {
//        a list of type Point to store final list
        List<Point> setOfPoints = new ArrayList<>();
//        loops through received list parameter and creates a new Point object for each point
//        by using Double[0] for Point.x and Double[1] for Point.y
        for (Double [] pointInArrayFormat: listOfPoints) {
            setOfPoints.add(doubleToPoint(pointInArrayFormat));
        }
//        returns final list
        return setOfPoints;
    }
}
