/*
Author: Daniel Beiers c3039134
Date:
Project: SENG2200 Assignment 1
Description: This class takes a text file as a command line argument and parses the values as points of a polygon with the
                capability of storing multiple polygons in a linked list.
                Data in text file to be "P (indicating start of polygon) int (representing number of sides) (series of white spaced doubles representing the vertices)"
 */
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class A1 {

    public static void main(String[] args) {

        //A new list object created to hold data
        MyPolygons unsortedList = new MyPolygons();

        try {
            File file_ = new File(args[0]);
            Scanner myReader = new Scanner(file_);
            //Loop to parse the data within the text file.
            while (myReader.hasNext()) {
                String data = myReader.next();
                //Conditional statement to parse for the start of the correct data
                if(data.compareTo("P") == 0) {
                    //Read for number of sides
                    int numSides = Integer.parseInt(myReader.next());

                    Polygon poly_ = new Polygon();
                    //Loop to add points to the polygon until number of sides is reached.
                    for (int p = 0; p < numSides; p++) {
                        double tempX = Double.parseDouble(myReader.next());
                        double tempY = Double.parseDouble(myReader.next());
                        poly_.addPoint(new Point(tempX, tempY));
                    }
                    unsortedList.append(poly_);
                }
            }
            System.out.println("Unsorted list");
            System.out.println(unsortedList);

            System.out.println("Sorted list");
            System.out.println((unsortedList.insertInOrder()));
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with locating file.");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("An error occurred reading from the file. Data might be incorrect format");
            e.printStackTrace();
        } catch (Exception e){
            System.out.println("An unknown error occurred.");
            e.printStackTrace();
        }

    }
}
