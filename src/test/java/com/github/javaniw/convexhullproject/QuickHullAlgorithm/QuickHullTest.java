package com.github.javaniw.convexhullproject.QuickHullAlgorithm;

import com.github.javaniw.convexhullproject.QuickHullApplication;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuickHullTest {

    @Test
    void quickHull() {
        List<Point> points = new ArrayList<>();
        points.add(new Point (1.0, 1.0));
        points.add(new Point(1.0, 6.0));
        points.add(new Point(2.0, 2.0));
        points.add(new Point(2.0, 4.0));
        points.add(new Point(3.0, 2.0));
        points.add(new Point(3.0, 3.0));
        points.add(new Point(3.0, 5.0));
        points.add(new Point(3.0, 7.0));
        points.add(new Point(4.0, 4.0));
        points.add(new Point(4.0, 6.0));

        List<Point> convexHull = QuickHull.quickHull(points);
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }

    }

    @Test
    void direction() {
    }
}