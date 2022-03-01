/*
This class defines a node to be used as an Polygon that references an 'Polygon' load, and also points to a
'next' and 'previous' node Polygons.
 */
public class Node <T>{
    //Private variables indicating the node pointers and the payload of the node.
    private Node prev;
    private Node next;
    private T data;

    //Empty constructor
    public Node(){
        prev = null;
        next = null;
        data = null;
    }

    //Constructor with predefined parameter arguments
    public Node(Node next, Node prev, T data){
      this.next = next;
      this.prev = prev;
      this.data = data;
    }

    //Generic getters and setters for nodes and payload
    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setData(T data) {
        this.data = data;
    }
}
