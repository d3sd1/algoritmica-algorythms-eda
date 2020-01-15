package Tema3.ProblemaEntrega.Lista;


public class NodeSimple<T> implements Node<T> {

	private T elem;
	private Node<T> next;
	
	public NodeSimple(T e) {
		elem = e;
	}

	public T getElem() {
		return elem;
	}

	public void setElem(T elem) {
		this.elem = elem;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}

