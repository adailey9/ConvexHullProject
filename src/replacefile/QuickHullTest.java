package com.github.javaniw.convexhullproject.QuickHullAlgorithm;

import com.github.javaniw.convexhullproject.HelperClasses.GeneratePoints;
import com.github.javaniw.convexhullproject.HelperClasses.PointListConversion;
import com.github.javaniw.convexhullproject.QuickHullApplication;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class QuickHullTest {

    @Test
    public static void main(String[] args)
    {
       // establishes scanner
        Scanner s= new Scanner(System.in);
        // asks user for a input
        System.out.println("Enter the number of points");
//the number user enter is set as the numOfPOints
        int numOfPoints = s.nextInt();
        // random  points are generated into the array list
        List<Double[]> setOfPoints = GeneratePoints.generate(numOfPoints, -95, 95);
        // the double arraylist is converted into a point  array list that becomes the convex
        List<Point> convexHull = QuickHull.quickHull(PointListConversion.doubleListToPointList(setOfPoints));
        // establishes the time before the critical code is executed
        long start = System.currentTimeMillis();
        System.out.println("Convex Hull:");
        // prints out the convex hull
        for (Point p : convexHull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
        long end = System.currentTimeMillis();
        //finds the amount of time that elapsed
        float sec = (end - start) / 1000F;
      // prints out the elasped time in seconds
        System.out.println("Seconds: "+sec);
    }


}