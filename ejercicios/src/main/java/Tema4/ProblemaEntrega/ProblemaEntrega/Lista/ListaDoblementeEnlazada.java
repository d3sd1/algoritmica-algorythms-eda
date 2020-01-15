package Tema4.ProblemaEntrega.ProblemaEntrega.Lista;


import java.util.Random;

public class ListaDoblementeEnlazada<T> implements Lista<T> {

    NodeDouble<T> header;
    NodeDouble<T> trailer;
    int size = 0;

    public ListaDoblementeEnlazada() {
        header = new NodeDouble(null);
        trailer = new NodeDouble(null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }


    public void addFirst(T elem) {
        NodeDouble newNode = new NodeDouble(elem);
        newNode.setNext(header.getNext());
        newNode.setPrev(header);
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
        size++;
    }


    public void addLast(T elem) {
        NodeDouble newNode = new NodeDouble(elem);
        newNode.setNext(trailer);
        newNode.setPrev(trailer.getPrev());
        trailer.getPrev().setNext(newNode);
        trailer.setPrev(newNode);
        size++;
    }


    // Parece que este método falla, pero lo cogí tal cual del ejercicio añadiéndole los setters/getters y TypeTokens.
    public void insertAt(int index, T elem) {
        NodeDouble newNode = new NodeDouble(elem);
        int i = 0;
        boolean inserted = false;
        for (NodeDouble<T> nodeIt = header; nodeIt != trailer && inserted == false; nodeIt = nodeIt.getNext()) {
            if (i == index) {
                newNode.setNext(nodeIt.getNext());
                newNode.setPrev(nodeIt);
                nodeIt.getNext().setPrev(newNode);
                nodeIt.setNext(newNode);
                inserted = true;
                size++;
            }
            ++i;
        }
        if (!inserted) System.out.println("DList: Insertion out of bounds");
    }


    public boolean isEmpty() {
        return (header.getNext() == trailer);
    }


    public boolean contains(T elem) {
        boolean found = false;
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer && found == false; nodeIt = nodeIt.getNext()) {
            if (nodeIt.getElem().equals(elem)) {
                found = true;
            }
        }
        return found;
    }


    public int getIndexOf(T elem) {
        int index = -1;
        int pos = 0;
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer && index == -1; nodeIt = nodeIt.getNext()) {
            if (nodeIt.getElem().equals(elem)) {
                index = pos;
            }
            ++pos;

        }
        return index;
    }


    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("DList: List is empty");
            return;
        }
        header.setNext(header.getNext().getNext());
        header.getNext().setPrev(header);
        size--;
    }


    public void removeLast() {
        if (isEmpty()) {
            System.out.println("DList: List is empty");
            return;
        }
        trailer.setPrev(trailer.getPrev().getPrev());
        trailer.getPrev().setNext(trailer);
        size--;
    }


    public void removeAll(T elem) {
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer; nodeIt = nodeIt.getNext()) {
            if (nodeIt.getElem().equals(elem)) {
                nodeIt.getPrev().setNext(nodeIt.getNext());
                nodeIt.getNext().setPrev(nodeIt.getPrev());
                size--;

            }
        }
    }


    public void removeAt(int index) {
        int i = 0;
        boolean removed = false;
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer && removed == false; nodeIt = nodeIt.getNext()) {
            if (i == index) {
                nodeIt.getPrev().setNext(nodeIt.getNext());
                nodeIt.getNext().setPrev(nodeIt.getPrev());
                removed = true;
                size--;

            }
            ++i;
        }
        if (!removed) System.out.println("DList: Deletion out of bounds");
    }


    public int getSize() {

        return size;
    }


    public T getFirst() {
        T result = null;
        if (isEmpty()) {
            System.out.println("DList: List is empty");
        } else result = header.getNext().getElem();
        return result;
    }

    public T getLast() {
        T result = null;

        if (isEmpty()) {
            System.out.println("DList: List is empty");
        } else result = trailer.getPrev().getElem();

        return result;
    }


    public T getAt(int index) {
        int i = 0;
        T result = null;
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer && result == null; nodeIt = nodeIt.getNext()) {
            if (i == index) {
                result = nodeIt.getElem();
            }
            ++i;
        }
        if (result == null) System.out.println("DList: Get out of bounds");
        return result;
    }

    public String toString() {
        String result = null;
        for (NodeDouble<T> nodeIt = header.getNext(); nodeIt != trailer; nodeIt = nodeIt.getNext()) {
            if (result == null) {
                result = String.valueOf(nodeIt.getElem());
            } else {
                result += "," + String.valueOf(nodeIt.getElem());
            }
        }
        return result == null ? "empty" : result;
    }


    @Override
    public T getHeader() {
        return !isEmpty() ? this.header.getElem() : null;
    }


    @Override
    public T getTrailer() {
        return !isEmpty() ? this.trailer.getElem() : null;
    }


    @Override
    public Node<T> getNodeAt(int index) {
        int i = 0;
        for (NodeDouble<T> actualNode = header; actualNode != null; actualNode = actualNode.getNext()) {
            if (i == index) {
                return actualNode;
            }
            ++i;
        }

        System.out.println("ListaSimple: Get out of bounds");
        return null;
    }

    @Override
    public NodeDouble<T> getElems() {
        return this.header;
    }

    public void setHeader(NodeDouble<T> header) {
        this.header = header;
    }
}