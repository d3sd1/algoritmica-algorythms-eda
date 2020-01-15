package Tema3.ProblemaEntrega.Lista;;

interface Lista<T> {
	void addFirst(T newElem);
	void addLast(T newElem);
	void insertAt(int index, T newElem);
	boolean isEmpty();
	boolean contains(T elem);
	int getSize();
	int getIndexOf(T elem);
	T getFirst();
	T getLast();
	T getAt(int index);
	String toString();
	void removeFirst();
	void removeLast();
	void removeAll(T elem);
	void removeAt(int index);
	/* Extra: Evita redundancia y permite obtener un nodo */
	Node getNodeAt(int index);
	/* EXTRA: Iterador */
	Node<T> getElems();
}


