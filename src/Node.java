/*
Author: Daniel Beiers c3039134
Date:
Project: SENG2200 Assignment 1
Description: This class defines a node to be used as data carrier that references an object payload, and also points to a
'next' and 'previous' node.
 */
public class Node {
    //Private variables indicating the node pointers and the payload of the node.
    private Node prev;
    private Node next;
    private Polygon data;

    //Empty constructor
    public Node(){
        prev = null;
        next = null;
        data = null;
    }

    //Generic getters and setters for nodes and payload
    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public Polygon getData() {
        return data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setData(Polygon data) {
        this.data = data;
    }
}
