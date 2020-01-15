package Tema2.EjercicioEntregaB;

public interface PilaBasica<T> {
    void push(T e);
    T top();
    T pop();
    int size();
    boolean isEmpty();
}
