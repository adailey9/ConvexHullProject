package com.github.javaniw.convexhullproject.HelperClasses;

import com.github.javaniw.convexhullproject.QuickHullAlgorithm.Point;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CounterClockwise {

    /**
     * Receives a list of points (x, y) and calculates the center point of the received points
     *
     * @param points a list of all the points in which the center will be calculated
     * @return the center point of all the points in the received list of points
     */
    public static Double[] calculateCenterPoint(List<Double []> points) {
//            Declares two double variables which will hold the sum of all the x and y coordinates of the
//            points in the list
        Double x = 0.0, y = 0.0;
//            loop that sums the x and y coordinates of all the points in the list
        for (Double [] point : points) {
            x += point[0];
            y += point[1];
        }
        Double centerPointX = x / points.size();
        Double centerPointY = y / points.size();
        return new Double[]{centerPointX, centerPointY};
    }

    /**
     * Returns a reordered list of Point objects in counterclockwise order
     *
     * @param points a list of Point objects
     * @return a list of Points objects sorted counterclockwise
     */
    public static List<Point> orderPoints(List<Point> points) {
//        converts the list of Points to a list of Doubles
        List<Double[]> pointsInDoubleFormat = PointListConversion.pointListToDoubleList(points);
//        uses the order() method to reorder the list of doubles and then converts them back to
//        a list of Point objects and returns the new list
        return PointListConversion.doubleListToPointList(order(pointsInDoubleFormat));
    }

    /**
     *
     * Returns a reordered list of points in counterclockwise order
     *
     * @param points a list of points (x, y)
     * @return a list points ordered in counterclockwise according to the center of the points
     */
    public static List<Double[]> order(List<Double []> points) {
        Double [] center = calculateCenterPoint(points);
        Collections.sort(points, new sortPointsCounterClockwise(center));
        return points;
    }

     static class sortPointsCounterClockwise implements Comparator<Double []> {
//         Points shall be sorted in regard to a specified center point
        private Double[] center;

         public sortPointsCounterClockwise(Double[] center) {
             this.center = center;
         }

         @Override
         public int compare(Double[] point1, Double[] point2) {
//             uses atan2 to calculate angle of point in regard to the center point
             Double angleOfPoint1 = Math.toDegrees(Math.atan2(point1[1] - center[1], point1[0] - center[0]));
             Double angleOfPoint2 = Math.toDegrees(Math.atan2(point2[1] - center[1], point2[0] - center[0]));

//             assures that all angles are measured in positive degrees
             if (angleOfPoint1 < 0)
                 angleOfPoint1 += 360;

             if (angleOfPoint2 < 0)
                 angleOfPoint2 += 360;

//             sorts points in counterclockwise order. Points of the same angle size will be sorted in order
//             they appear in list
             if (angleOfPoint1 < angleOfPoint2)
                 return -1;
             return 1;
         }
     }

}
