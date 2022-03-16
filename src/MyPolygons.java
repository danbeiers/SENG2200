/*
Author: Daniel Beiers c3039134
Date: 16.3.22
Project: SENG2200 Assignment 1
Description: A circular linked list that uses a sentinel node, which carries no data, that acts as the head and the tail.
 */
public class MyPolygons {
    private Node current;
    private Node sentinel;
    private int size;

    public MyPolygons(){
        this.sentinel = new Node();
        this.sentinel.setNext(sentinel);
        this.sentinel.setPrev(sentinel);
        this.size = 0;
        this.current = this.sentinel;
    }

    //Standard getters and setters
    public Node getCurrent() {
        return this.current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }

    public Node getSentinel() {
        return this.sentinel;
    }

    public void setSentinel(Node sentinel) {
        this.sentinel = sentinel;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //This method inserts an object into the start of the list. Current will point to the first item in the list.
    //Checks for empty node and non-empty node. Increments size of the list.
    public void prepend(Polygon data_){
        Node newNode = new Node();
        if(size == 0){
            newNode.setPrev(this.sentinel);
            newNode.setNext(this.sentinel);
            this.sentinel.setNext(newNode);
            this.sentinel.setPrev(newNode);
        }
        else{
            newNode.setPrev(this.sentinel);
            newNode.setNext(this.sentinel.getNext());
            (this.sentinel.getNext()).setPrev(newNode);
            this.sentinel.setNext(newNode);
        }
        this.size++;
        this.current = newNode;
        newNode.setData(data_);

    }

    //This method inserts an item into the end of the list. Current will point to the first item in the list.
    //Checks for empty node and non-empty node. Increments size of the list.
    public void append(Polygon data_){
        Node newNode = new Node();

        if(size == 0){
            newNode.setNext(this.sentinel);
            newNode.setPrev(this.sentinel);
            this.sentinel.setPrev(newNode);
            this.sentinel.setNext(newNode);
        }
        else{
            newNode.setNext(this.sentinel);
            newNode.setPrev(this.sentinel.getPrev());
            (this.sentinel.getPrev()).setNext(newNode);
            this.sentinel.setPrev(newNode);
        }
        this.size++;
        this.current = this.sentinel.getNext();
        newNode.setData(data_);
    }

    //This method inserts an item before the node that the 'current' pointer identifies. 'Current' will stay where it is.
    //Checks for start or end of list and calls prepend or append respectively. Increments size of the list if not start or end.
    public void insert(Polygon data){
        if(this.current.getPrev() == this.sentinel){
            prepend(data);
        }else if(this.current.getNext() == this.sentinel){
            append(data);
        }else {
            Node newNode = new Node();
            newNode.setData(data);
            newNode.setNext(this.current);
            newNode.setPrev(this.current.getPrev());
            this.current.getPrev().setNext(newNode);
            this.current.setPrev(newNode);
            this.size++;
        }
    }

    //Moves 'current' pointer to the next node in the list.
    public void stepNext(){
        this.current = this.current.getNext();
    }

    //Sets 'current' pointer to the first node in the list
    public void currentToHead(){
        this.current = this.sentinel.getNext();
    }

    //Removes the node at 'current' pointer and inserts it at the start of the list with the prepend method.
    //The 'current' pointer remains pointing at the moved node.
    public void resetToHead(){
        this.current.getPrev().setNext(this.current.getNext());
        this.current.getNext().setPrev(this.current.getPrev());
        prepend(this.current.getData());
    }

    //Removes the node at the start of the list and returns the data object the node was carrying.
    //The 'current' pointer remains where it is and size is decremented.
    public Polygon takeHead(){
        Node newNode = this.sentinel.getNext();
        newNode.getNext().setPrev(this.sentinel);
        this.sentinel.setNext(newNode.getNext());
        this.size--;
        return newNode.getData();
    }

    //This method returns a new MyPolygons object with the polygons sorted in ascending area.
    public MyPolygons insertInOrder(){

        MyPolygons unsortedList = this;
        MyPolygons sortedList = new MyPolygons();

        //If the list contains less than two polygons no sorting is required.
        if(unsortedList.getSize() >= 2) {
            //Set current pointer of unsorted list to first node in unsorted list
            unsortedList.currentToHead();
            //Loop until current is point to the sentinel which means all nodes have been copied to new sorted list
            while(unsortedList.getCurrent() != unsortedList.getSentinel()){
                //If sorted list is empty, insert first node from unsorted list
                if(sortedList.getSize() == 0) {
                    sortedList.prepend(unsortedList.getCurrent().getData());
                }
                else{
                    boolean added = false;
                    //Loops until the data is added
                    while(!added){
                        //Compares the next data object to be added with the data object at 'current' pointer in the sorted listed.
                        boolean result = (unsortedList.getCurrent().getData()).ComesBefore(sortedList.getCurrent().getData());
                        //If the polygon is equal it is inserted before the 'current' node in the list.
                        if(result) {
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

    //The toString method is overridden to return a string containing the data of every node in the list.
    //Requires a toString method in the object class to interpret the object data.
    @Override
    public String toString(){
        String output = "";
        this.current = this.sentinel;
        this.stepNext();
        if(this.current.getData() != null) {
            while (this.current != this.sentinel) {
                output = output + (current.getData()).toString() + "\n";
                this.stepNext();
            }
        }
        return output;
    }
}
