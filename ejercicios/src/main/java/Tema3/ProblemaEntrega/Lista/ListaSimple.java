package Tema3.ProblemaEntrega.Lista;


import java.lang.reflect.Array;

public class ListaSimple<T> implements Lista<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;


    public ListaSimple() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Node<T> getElems() {
        return this.first;
    }
    @Override
    public void addFirst(T newElem) {
        Node newNode = new NodeSimple(newElem);
        if (this.isEmpty()) {
            this.first = newNode;
            this.last = newNode;
        } else {
            newNode.setNext(this.first);
            this.first = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T newElem) {
        if (this.isEmpty()) {
            this.addFirst(newElem);
        } else {
            Node node = new NodeSimple(newElem);
            this.last.setNext(node);
            this.last = node;
            this.size++;
        }
    }


    @Override
    public void insertAt(int idx, T newElem) {
        System.out.println("adding : " + newElem + " at position: " + idx);
        Node newNode = new NodeSimple(newElem);
        if (idx == 0) {
            addFirst(newElem);
        } else if (idx == size) {
            addLast(newElem);
        } else {
            int i = 1;
            boolean inserted = false;
            for (Node nod = first; nod != null && inserted == false; nod = nod.getNext()) {
                if (i == idx) {
                    newNode.setNext(nod.getNext());
                    nod.setNext(newNode);
                    inserted = true;
                    size++;
                }
                ++i;
            }
            if (!inserted) System.out.println("ListaSimple: Insertion out of bounds");
        }
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean contains(T elem) {
        for (Node nod = first; nod != null; nod = nod.getNext()) {
            if (nod.getElem().equals(elem)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeFirst() {
        if (!this.isEmpty()) {
            this.first = this.first.getNext();
            if (this.first == null) {
                this.last = null;
            }
            this.size--;
        }
    }

    @Override
    public void removeLast() {
        if (!this.isEmpty()) {
            if (this.size == 1) {
                this.removeFirst();
            } else {
                Node nod = this.first;
                while (nod.getNext() != this.last) {
                    nod = nod.getNext();
                }
                nod.setNext(null);
                this.last = nod;
                this.size--;
            }
        }
    }


    @Override
    public void removeAll(T elem) {
        for (int i = 0; i < this.size; i++) {
            T actual = this.getAt(i);
            if (actual.equals(elem)) {
                this.removeAt(i);
            }
        }
    }

    @Override
    public void removeAt(int idx) {
        System.out.println("[DEBUG] RM POS: " + idx);
        if (0 == idx) {
            removeFirst();
        } else if (size - 1 == idx) {
            removeLast();
        } else {
            Node nextNode = null;
            if(null != nextNode) {
                nextNode = this.getNodeAt(idx + 1);
            }
            Node prevNode = null;
            if(null != prevNode) {
                prevNode = this.getNodeAt(idx - 1);
                prevNode.setNext(nextNode);
            }
            System.out.println("[DEBUG] RM SUCCESS: "  + idx);
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getIndexOf(T elem) {
        System.out.println("index of " + elem);

        int pos = 0;
        for (Node node = first; node != null; node = node.getNext()) {
            if (node.getElem().equals(elem)) {
                return pos;
            }
            ++pos;
        }
        return -1;
    }

    @Override
    public T getFirst() {
        return !isEmpty() ? this.first.getElem() : null;
    }


    @Override
    public T getLast() {
        return !isEmpty() ? this.last.getElem() : null;
    }

    @Override
    public T getAt(int index) {
        return this.getNodeAt(index).getElem();
    }

    @Override
    public Node<T> getNodeAt(int index) {
        int i = 0;
        for (Node actualNode = first; actualNode != null; actualNode = actualNode.getNext()) {
            if (i == index) {
                return actualNode;
            }
            ++i;
        }

        System.out.println("ListaSimple: Get out of bounds");
        return null;
    }

    @Override
    public String toString() {
        String result = null;
        for (Node nod = first; nod != null; nod = nod.getNext()) {
            if (result == null) {
                result = nod.getElem().toString();
            } else {
                result += "," + nod.getElem().toString();
            }
        }
        return result == null ? "empty" : result;
    }
}
