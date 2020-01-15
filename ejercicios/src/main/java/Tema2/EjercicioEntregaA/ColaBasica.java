package Tema2.EjercicioEntregaA;

public interface ColaBasica<T> {
    boolean isEmpty();
    void enqueue(T e, int prio);
    T dequeue();
    T front();
    int getSize();
}
