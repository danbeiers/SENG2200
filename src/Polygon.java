/*
Author: Daniel Beiers c3039134
Date: 16.3.22
Project: SENG2200 Assignment 1
Description:    This class is an object designed to hold information regarding the vertices of a polygon.
                An array is initialised, and each element is a Point object.
*/
public class Polygon implements ComparePoly{

    //Private variables
    //'originPoint' holds a Point that is determined to be closest to the origin. Updated as every Point is added.
    //'vertices' is an array that holds the vertices of the polygon.
    //'TOLERANCE_FACTOR' is the value by which polygons are compared and attributed to be the same area.
    private Point originPoint;
    private Point[] vertices;
    private final double TOLERANCE_FACTOR = 0.001;

    public Polygon(){
        originPoint = null;
        vertices = new Point[0];
    }

    //Standard getters and setters
    public Point getOriginPoint() {
        return originPoint;
    }

    public void setOriginPoint(Point originPoint) {
        this.originPoint = originPoint;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    //This method takes a Point object as a parameter. The current 'vertices' array is replicated and size incremented.
    //The new Point is then added to the last index of the array.
    //The new Point's distance to the origin is calculated and compared to the current lowest value. If less, new Point is stored in 'originPoint'.
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

    //This method uses the shoelace theorem to calculate the area of the polygon.
    //If the polygon contains less than 2 points, the method returns zero, else returns the area as a double floating point.
    public double area(){
        double sum = 0;
        if(vertices.length > 1) {
            for (int i = 0; i < vertices.length - 1; i++) {

                double x1 = vertices[i].getX();
                double y1 = vertices[i].getY();
                double x2 = vertices[i + 1].getX();
                double y2 = vertices[i + 1].getY();

                sum = sum + ((x2 + x1) * (y2 - y1));
                if (i == vertices.length - 2) {
                    sum += ((vertices[0].getX() + x2) * (vertices[0].getY() - y2));
                }
            }
        }
        sum = Math.abs(sum) / 2;

        return sum;
    }

    //The toString method is overridden to return the array elements as a string with the area appended to the end to two decimal places.
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

    //A Polygon object is passed to this implementation of the ComparePoly class method.
    //A percentage, as a decimal, of the smallest polygon is used to evaluate the tolerance in area difference.
    //If the areas are within this value, polygons are equated to have the same area.
    //If area is the same, the Polygon with vertice closest to the origin is assumed to have a 'less than' equivalent and a negative integer returned.
    //If the passed parameter Polygon has a greater area or greater distance from origin it is identified as being 'greater than' and a positive integer returned
    //If the polygons have equal area and the same vertices of equal distance to the origin than they are equated to be 'equal' and a zero is returned.
    public boolean ComesBefore(Polygon o) {
        double tolerance;
        if (this.area() <= o.area()) {
            tolerance = this.area() * TOLERANCE_FACTOR;
        } else {
            tolerance = o.area() * TOLERANCE_FACTOR;
        }

        if (Math.abs(o.area() - this.area()) <= tolerance) {
            if (this.originPoint.distanceToOrigin() == o.originPoint.distanceToOrigin())
                return true;
            else if (this.originPoint.distanceToOrigin() < o.originPoint.distanceToOrigin())
                return true;
            else if (this.originPoint.distanceToOrigin() > o.originPoint.distanceToOrigin())
                return false;
        }
        else return this.area() < o.area();

        return false;
    }
}
