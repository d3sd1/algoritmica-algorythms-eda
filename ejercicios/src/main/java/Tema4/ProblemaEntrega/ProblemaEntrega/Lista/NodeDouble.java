package Tema4.ProblemaEntrega.ProblemaEntrega.Lista;


public class NodeDouble<T> implements Node<T> {

	private T elem;
	private NodeDouble<T> next;
	private NodeDouble<T> prev;

	public NodeDouble(T e) {
		elem = e;
	}

	@Override
	public T getElem() {
		return elem;
	}

	@Override
	public void setElem(T elem) {
		this.elem = elem;
	}

	public NodeDouble<T> getNext() {
		return next;
	}

	public void setNext(NodeDouble<T> next) {
		this.next = next;
	}

	public NodeDouble<T> getPrev() {
		return prev;
	}

	public void setPrev(NodeDouble<T> prev) {
		this.prev = prev;
	}
}

