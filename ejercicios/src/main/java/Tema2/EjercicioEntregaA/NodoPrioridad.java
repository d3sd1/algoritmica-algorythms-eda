package Tema2.EjercicioEntregaA;

public class NodoPrioridad<T> {
    private T elem;
    private int prio;
    private NodoPrioridad<T> nextNode;

    public NodoPrioridad(T elem) {
        this.elem = elem;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoPrioridad<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(NodoPrioridad<T> nextNode) {
        this.nextNode = nextNode;
    }

    public int getPrio() {
        return prio;
    }

    public void setPrio(int prio) {
        this.prio = prio;
    }

    @Override
    public String toString() {
        return "NodoPrioridad{" +
                "elem=" + elem +
                ", prio=" + prio +
                ", nextNode=" + nextNode +
                '}';
    }
}
