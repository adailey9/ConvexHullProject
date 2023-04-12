package com.github.javaniw.convexhullproject.QuickHullAlgorithm;

import com.github.javaniw.convexhullproject.HelperClasses.CounterClockwise;
import com.github.javaniw.convexhullproject.HelperClasses.PointCompartive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuickHull {
    // static so it can be cleared and called upon in other classes
    static public List<Point> convexHull = new ArrayList<>();

    public static List<Point> quickHull(List<Point> listOfPoints) {
        // clears convexhull so that is empty before adding points
        convexHull.clear();
        // sorts the listofpoints so they can compare each other in the  Pointcompartive class
        Collections.sort(listOfPoints, new PointCompartive());
        // establishes the a as the most leftest and b as the rightest of the list
        Point a = listOfPoints.get(0);
        Point b = listOfPoints.get(listOfPoints.size() - 1);
        // adds points into convexhull
        convexHull.add(a);
        convexHull.add(b);
        // Creates new arrays s1 and s2
        List<Point> s1 = new ArrayList<Point>();
        List<Point> s2 = new ArrayList<Point>();
        // for each loop that loops through the listof points for  a to b in direction for s1 and b to a for s2
        for (Point i : listOfPoints) {
            if(i.equals(a) || i.equals(b))
                continue;
                if (direction(a, b, i) > 0) {
                    s1.add(i);
                } else if (direction(a, b, i) < 0) {
                    s2.add(i);
                }
            }
        // calls the findhull methods from the FindHull class  so it can go through it methods
        FindHull.findHull(s1, a, b);
        FindHull.findHull(s2, b, a);

        if (convexHull.isEmpty()) {
            return null;
        }
        return CounterClockwise.orderPoints(convexHull);
    }
    // direction method which purpose to establish which direction the line goes
    public static int direction(Point p1, Point p2, Point p3) {

        double a = p2.x - p1.x;
        double b = p2.y - p1.y;
        double c = p3.x - p1.x;
        double d = p3.y - p1.y;

// Determining cross Product
        double cross_product = a * d - b * c;

// return 1 if cross product is positive i.e., point is on the right of the line segment
        if (cross_product > 0)
            return 1;

// return -1 if cross product is negative i.e., point is on the left of the line segment
        if (cross_product < 0)
            return -1;

// return 0 if cross product is zero i.e., point is on the line segment
        return 0;
    }



}