package com.github.javaniw.convexhullproject.QuickHullAlgorithm;

public class Point
{
    // establishes the variable x and y
    public double x;
    public double y;
    // constructor that makes easier for others to call
    public  Point( double x ,double y)
    {
        this.x =x;
        this.y=y;
    }

// to string establish so we can print out the points
    public String toString(){
        return "{ " + x+ ", " + y + " }";
    }
}
