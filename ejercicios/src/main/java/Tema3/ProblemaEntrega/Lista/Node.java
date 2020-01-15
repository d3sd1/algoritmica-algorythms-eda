package Tema3.ProblemaEntrega.Lista;

public interface Node<T> {
    public T getElem();

    public void setElem(T elem);

    public Node<T> getNext();

    public void setNext(Node<T> next);
}
