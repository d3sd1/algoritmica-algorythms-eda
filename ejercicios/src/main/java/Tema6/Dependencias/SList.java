package Tema6.Dependencias;

public class SList {
    public SNode first;
    // no usamos size ni last, por simplicidad de las soluciones
// ===================================================================
    public boolean contains(int n) {
        return contains(first, n);
    }
    public boolean contains(SNode sublist, int n) {
        if (sublist == null) {
            return false;
        } else if (sublist.elem == n) {
            return true;
        } else {
            return contains(sublist.next, n);
        }
    }
    // ===================================================================
    public int size() {
        return size(first);
    }
    public int size(SNode sublist) {
        if (sublist == null) {
            return 0;
        } else {
            return size(sublist.next) + 1;
        }
    }
    // ===================================================================
    public void insertAt(int pos, int n) {
// reemplaza la sublista "first" por la misma sublista, en la
// que se ha insertado el elemento en la posición pos
        first = insertAt(first, pos, n);
    }
    // Contrato del método "insertAt":
// - devuelve una sublista (representada por un SNode) consistente
// en la sublista que se pasó a la que se ha insertado un nodo en
// la posición "pos"
// - si pos = 0, va a devolver el nuevo nodo (y la sublista antigua detrás)
// - si pos > 0, va a devolver el mismo nodo (pero el nuevo nodo va
// a estar después)
    private SNode insertAt(SNode sublist, int pos, int n) {
        if (pos == 0) {
            SNode newNode = new SNode(n);
            newNode.next = sublist;
            return newNode;
        } else {
// si entendemos "sublist.next" como la sublista después del
// nodo "sublist",
// reemplaza la sublista "sublist.next" por la misma sublista en la
// que se ha insertado el elemento en la posición pos...
// ... que es justo lo que pone en el contrato de "insertAt"
            sublist.next = insertAt(sublist.next, pos - 1, n);
            return sublist;
        }
    }
    // ===================================================================
    public String toString() {
        return "[ " + toString(first) + "]";
    }
    public String toString(SNode sublist) {
        if (sublist == null) {
            return "";
        } else {
            return sublist.elem + " " + toString(sublist.next);
        }
    }
    // ===================================================================
    public static void main(String[] args) {
        SList list = new SList();
        list.first = new SNode(3, new SNode(2, new SNode(5)));
        System.out.println(list.toString());
        System.out.println(list.contains(2));
        System.out.println(list.contains(3));
        System.out.println(list.contains(5));
        System.out.println(list.contains(6));
        list.insertAt(0, 6);
        System.out.println(list.toString());
        System.out.println(list.contains(6));
        list.insertAt(3, 8);
        System.out.println(list.toString());
        System.out.println(list.contains(8));
    }
}