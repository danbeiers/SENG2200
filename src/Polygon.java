/*
Author: Daniel Beiers c3039134
Date:
Project: SENG2200 Assignment 1
Description:
*/
public class Polygon implements Comparable<Polygon>{

    private Point originPoint;
    private Point[] vertices;

    public Polygon(){
        originPoint = null;
        vertices = new Point[0];
    }

    public Polygon(int totalSides){
        originPoint = null;
        vertices = new Point[totalSides];
        for(int p = 0; p < totalSides;p++){
            vertices[p] = new Point();
        }
    }

    public int getNumSides(){
        if(vertices.length == 0)
            return 0;
        else
            return vertices.length;
    }

    public double originDistance(){
        return originPoint.distanceToOrigin();
    }

    public void addPoint(Point point_){
        if(vertices.length == 0) {
            Point[] tempPoly = new Point[1];
            tempPoly[0] = point_;
            vertices = tempPoly;
            originPoint = point_;
        }
        else {
            Point[] tempPoly = new Point[vertices.length + 1];
            int i=0;
            for(Point p:vertices) {
                tempPoly[i] = p;
                i++;
            }
            tempPoly[i] = point_;
            vertices = tempPoly;
            if(point_.distanceToOrigin() < originPoint.distanceToOrigin())
                originPoint = point_;
        }
    }
    public double area(){
        //System.out.println("Vertices=" + vertices.length);
        double sum = 0;
        Point tempPoint = new Point(vertices[0].getX(),vertices[0].getY());
        for(int i = 0 ; i < vertices.length - 1; i++){

            double x1 = vertices[i].getX();
            double y1 = vertices[i].getY();
            double x2 = vertices[i+1].getX();
            double y2 = vertices[i+1].getY();

            sum = sum + ((x2+x1) * (y2-y1));
            if(i == vertices.length-2){
                sum += ((vertices[0].getX() + x2) * (vertices[0].getY()-y2));
            }

        }
        sum = Math.abs(sum) / 2;

        return sum;
    }

    @Override
    public String toString(){
        area();
        String output = "[";
        String area = "0.00";
        if(vertices.length != 0) {
            for (Point point_ : vertices) {
                output += point_.toString();
            }
            area = String.format("%6.2f", this.area());
        }
        output = output + "]: " + area;

        return output;
    }

    @Override
    public int compareTo(Polygon o) {
        double tolerance = 0.0;
        if (this.area() <= o.area()) {
            tolerance = this.area() * 0.001;
        } else {
            tolerance = o.area() * 0.001;
        }

        if (Math.abs(o.area() - this.area()) <= tolerance) {
            if (this.originPoint.distanceToOrigin() == o.originPoint.distanceToOrigin())
                return 0;
            else if (this.originPoint.distanceToOrigin() < o.originPoint.distanceToOrigin())
                return -1;
            else if (this.originPoint.distanceToOrigin() > o.originPoint.distanceToOrigin())
                return 1;
        }
        else if (this.area() < o.area())
            return -1;

        return 1;
    }
}
