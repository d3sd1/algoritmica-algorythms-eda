package Tema6.Dependencias;

public class SNode {
    int elem;
    SNode next;
    public SNode(int elem) {
        this.elem = elem;
    }
    public SNode(int elem, SNode next) {
        this.elem = elem;
        this.next = next;
    }
}