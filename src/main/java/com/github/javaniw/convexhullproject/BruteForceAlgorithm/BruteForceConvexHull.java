package com.github.javaniw.convexhullproject.BruteForceAlgorithm;

import com.github.javaniw.convexhullproject.ConvexHull.ConvexHull;
import com.github.javaniw.convexhullproject.HelperClasses.CounterClockwise;

import java.util.ArrayList;
import java.util.List;

public class BruteForceConvexHull extends ConvexHull {

    /**
     * Returns the convex hull of a convex set. The hull will be specified by the points that are connected
     * in counterclockwise order.
     *
     * @param listOfPoints a list of points in the graph (x coordinate, y coordinate)
     * @return a list of points of the convex hull in counterclockwise order
     */
    public static List<Double []> findConvexHull(List<Double[]> listOfPoints) {
        if (listOfPoints.size() <= 3) {
            return listOfPoints;
        }
//        initialize list that will contain the points that are a part of the convex hull of the set
        List<Double[]> convexHullPoints = new ArrayList<>();

//        loops over all points in the set
        for (Double [] point1 : listOfPoints) {
//            Double values which hold the x and y coordinates of point 1, respectively
            Double x1 = point1[0];
            Double y1 = point1[1];

//            loops over all points in the set other than point 1 from the preceding loop
            for (Double [] point2 : listOfPoints) {
//                if statement ensures that point 1 and point 2 are not the same point
                if (point2.equals(point1))
                    continue;

//                Double values which hold the x and y coordinates of point 2, respectively
                Double x2 = point2[0];
                Double y2 = point2[1];

//                calculates a, b, and c using: a = y2 − y1, b = x1 − x2, c = x1y2 − y1x2
                Double a = y2 - y1;
                Double b = x1 - x2;
                Double c = (x1 * y2) - (y1 * x2);

//                boolean values which represent if any of the remaining points in the set have a negative sign or
//                a positive sign. Initialized to false because at first there is not a point
//                which is negative nor positive.
                boolean hasNegatives = false, hasPositives = false;
//                loops over all points in the set other than point 1 and point 2 from the preceding loop
                for (Double [] pointX : listOfPoints) {
//                    if statement ensures that point X is not one of the points that creates the line segment
                    if (pointX.equals(point1) || pointX.equals(point2))
                        continue;

//                    Double values which hold the x and y coordinate values of point X, which is any point from
//                    the set other than point 1 and point 2
                    Double x = pointX[0];
                    Double y = pointX[1];

//                    checks which side the other point is on by finding if the result of the
//                    equation is positive or negative
                    Double result = (a * x) + (b * y) - c;

//                    if result of equation is negative, make hasNegatives true
                    if (result < 0)
                        hasNegatives = true;
//                    if result of equation is positive, make hasPositives true
                    if (result > 0)
                        hasPositives = true;
//                    if both hasNegatives and hasPositives is true then break because the line segment is not
//                    a part of the convex hull, therefore, no need to check the rest of the points
                    if (hasNegatives && hasPositives)
                        break;
                }

//                if hasNegatives and hasPositives are not the same, then the line segment
//                is a part of the convex hull
                if (hasNegatives ^ hasPositives) {
//                    only add the points if they are not already added to the list.
                    if (!convexHullPoints.contains(point1))
                        convexHullPoints.add(point1);
                    if (!convexHullPoints.contains(point2))
                        convexHullPoints.add(point2);
                }
            }
        }
//        if the list is empty, return null to signal that the set is not a convex set and therefore cannot
//        have a convex hull
        if (convexHullPoints.isEmpty())
            return null;
//        otherwise, return the list of points
        return CounterClockwise.order(convexHullPoints);
    }
}
