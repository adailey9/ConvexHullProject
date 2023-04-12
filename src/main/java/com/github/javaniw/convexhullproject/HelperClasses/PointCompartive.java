package com.github.javaniw.convexhullproject.HelperClasses;

import com.github.javaniw.convexhullproject.QuickHullAlgorithm.Point;

import java.util.Comparator;

public class PointCompartive implements Comparator<Point> {

/// Compares x to x as points
    @Override
    public int compare(Point o1, Point o2) {
        return Double.compare(o1.x,o2.x);
    }
}
