/*
Author: Daniel Beiers c3039134
Date:
Project: SENG2200 Assignment 1
Description:
 */
public class MyPolygons <T>{
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

    //This method inserts an item into the start of the list. Current will point to the first item in the list.
    public void prepend(T data_){
        Node<T> newNode = new Node();
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
    public void append(T data_){
        Node<T> newNode = new Node<T>();


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

    public void insert(T data){
        if(this.current.getPrev() == this.sentinel){
            prepend(data);
        }else if(this.current.getNext() == this.sentinel){
            append(data);
        }else {
            Node<T> newNode = new Node<>();
            newNode.setData(data);
            newNode.setNext(this.current);
            newNode.setPrev(this.current.getPrev());
            this.current.getPrev().setNext(newNode);
            this.current.setPrev(newNode);
            this.size++;
        }
    }

    public void stepNext(){
        this.current = this.current.getNext();
    }
    public void currentToHead(){
        this.current = this.sentinel.getNext();
    }

    public void resetToHead(){
        this.current.getPrev().setNext(this.current.getNext());
        this.current.getNext().setPrev(this.current.getPrev());
        prepend((T) this.current.getData());
    }

    public T takeHead(){
        Node newNode = this.sentinel.getNext();
        newNode.getNext().setPrev(this.sentinel);
        this.sentinel.setNext(newNode.getNext());
        this.size--;
        return (T) newNode.getData();
    }

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
