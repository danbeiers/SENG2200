/*
Author: Daniel Beiers c3039134
Date: 16.3.22
Project: SENG2200 Assignment 1
Description: This class will hold the value of two floating point numbers as coordinates as private variables.
             These will be accessible via standard getter and setter methods.
             The class also contain an override method to return the point as a string and a method to calculate the distance back to the cartesian origin.
 */
import java.lang.Math;

public class Point {

    //Points are doubles representing coordinates on a cartesian plane.
    private double x;
    private double y;

    public Point(){
        x = 0.0d;
        y = 0.0d;
    }

    public Point(double x_, double y_){
        x = x_;
        y = y_;
    }

    //Standard getters and setters.
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //Method to calculate the distance to the origin using Pythagoras's theorem and return the value as a double.
    public double distanceToOrigin(){
        return Math.sqrt((x*x) + (y*y));
    }

    //This method overrides the standard toString method and returns a string representing the two coordinates truncated to two decimal points.
    @Override
    public String toString(){
        return String.format("(%4.2f , %4.2f)",x,y);
    }
}
