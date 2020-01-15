package Tema2.EjercicioEntregaA;

public abstract class NodoBasico<T> {
    private T elem;
    private NodoBasico<T> nextElem;

    public NodoBasico(T elem) {
        this.elem = elem;
    }

    public T getElem() {
        return elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public NodoBasico<T> getNextElem() {
        return nextElem;
    }

    public void setNextElem(NodoBasico<T> nextElem) {
        this.nextElem = nextElem;
    }

}
