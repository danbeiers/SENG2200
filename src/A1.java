import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class A1 {

    public static void main(String[] args) {

        MyPolygons unsortedList = new MyPolygons();

        try {
            //File file_ = new File("src/sample_in.txt");
            File file_ = new File("src/test2.txt");
            Scanner myReader = new Scanner(file_);
            while (myReader.hasNext()) {
                String data = myReader.next();
                if(data.compareTo("P") == 0) {
                    int next = Integer.parseInt(myReader.next());

                    Polygon poly_ = new Polygon();
                    for (int p = 0; p < next; p++) {
                        double tempX = Double.parseDouble(myReader.next());
                        double tempY = Double.parseDouble(myReader.next());
                        poly_.addPoint(new Point(tempX, tempY));
                    }
                    unsortedList.append(poly_);
                }
            }
            System.out.println("Unsorted list");
            System.out.println(unsortedList.toString());

            System.out.println("Sorted list");
            System.out.println((insertInOrder(unsortedList)).toString());
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static MyPolygons insertInOrder(MyPolygons list){

        MyPolygons unsortedList = list;
        MyPolygons sortedList = new MyPolygons();
        if(unsortedList.getSize() >= 2) {
            //Set current pointer of unsorted list to first node in unsorted list
            //unsortedList.setCurrent((unsortedList.getSentinel()).getNext());
            unsortedList.currentToHead();
            //Loop until current is point to the sentinel which means all nodes have been copied to new sorted list
            while(unsortedList.getCurrent() != unsortedList.getSentinel()){
                //If sorted list is empty, insert first node from unsorted list
                if(sortedList.getSize() == 0) {
                    //System.out.println("Polygon added at size = 0="+unsortedList.getCurrent().getData().toString());
                    sortedList.prepend(unsortedList.getCurrent().getData());
                }
                else{
                    boolean added = false;
                    while(!added){
                        int result = ((Polygon) unsortedList.getCurrent().getData()).compareTo((Polygon) sortedList.getCurrent().getData());
                        if(result == 0) {
                            sortedList.insert(unsortedList.getCurrent().getData());
                            added = true;
                        }else if(result < 0){
                            sortedList.insert(unsortedList.getCurrent().getData());
                            sortedList.currentToHead();
                            added = true;
                        }else{
                            sortedList.stepNext();
                            if(sortedList.getCurrent() == sortedList.getSentinel()){
                                sortedList.append(unsortedList.getCurrent().getData());
                                added = true;
                            }
                        }
                    }
                }
                unsortedList.stepNext();
            }

        }else{
            sortedList = unsortedList;
        }
        return sortedList;
    }
}
