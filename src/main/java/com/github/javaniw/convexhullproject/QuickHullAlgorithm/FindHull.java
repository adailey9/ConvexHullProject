package com.github.javaniw.convexhullproject.QuickHullAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class FindHull
{
    // furthestpoint which purpose is made for c as the furthest point must be found
    private static Point furthestPoint(List<Point> points, Point A, Point B) {
        double maxArea = Integer.MIN_VALUE;
        Point furthestPoint = null;
// for loop which goes through point ,so  the furthest point can be found
        for (Point P : points) {
            if (P.equals(A) || P.equals(B))
                continue;
            // the equation is establishes the area
            double area = Math.abs((A.x - P.x) * (B.y - A.y) - (A.x - B.x) * (P.y - A.y));
            // if stament that checks if area is greater than minimum and establishes the furthest point
            if (area >= maxArea) {
                maxArea = Math.abs((A.x - P.x) * (B.y - A.y) - (A.x - B.x) * (P.y - A.y));
                furthestPoint = P;
            }
        }
        return furthestPoint;
    }


    public static void findHull(List<Point> Sk, Point p, Point q) {
        // checks if the arralist of sk is empty
        if (Sk.isEmpty()) {
            return;
        }
        // establishes c to the furthest point by having equal what is returned from the furthestPoint method
        Point c = furthestPoint(Sk,p,q);
        // c is then added back into the convexhull in the quickhull class
        QuickHull.convexHull.add(c);
        // The arraylists of s1 and s2 are established
        List<Point> s1 = new ArrayList<Point>();
        List<Point> s2 = new ArrayList<Point>();
        // for each loop that loops through the Sk for  p to c in direction for s1 and c to q for s2
        for (Point i : Sk) {
            if(i.equals(p) || i.equals(q))
                continue;
            if (QuickHull.direction(p, c, i) > 0) {
                s1.add(i);
            } else if (QuickHull.direction(c,q, i) > 0) {
                s2.add(i);
            }
        }
// recursion so that it can call it self two times
            findHull(s1, p, c);
            findHull(s2, c, q);
    }
}
