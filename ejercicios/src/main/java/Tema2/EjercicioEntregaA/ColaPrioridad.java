package Tema2.EjercicioEntregaA;

import Tema2.EjercicioEntregaB.Node;

public class ColaPrioridad<T> implements ColaBasica<T> {
    private int numPrioridadMax;
    private NodoPrioridad<T> firstNode; //Se usa solo firstElem para usarlo como iterador y simplificar el ejercicio
    private NodoPrioridad<T> lastNode; //Se usa solo firstElem para usarlo como iterador y simplificar el ejercicio
    private int nodeCount;

    public ColaPrioridad(int numPrioridadMax) {
        this.numPrioridadMax = numPrioridadMax;
    }

    @Override
    public boolean isEmpty() {
        return this.firstNode == null;
    }


    @Override
    public void enqueue(T e, int prioridad) {
        NodoPrioridad<T> node = new NodoPrioridad<T>(e);

        /* Revisar que la prioridad sea correcta */
        prioridad--; // Ya que empezamos en 0
        if(prioridad < 0) {
            prioridad = 0;
        } else if(prioridad > this.numPrioridadMax) {
            prioridad = this.numPrioridadMax;
        }
        node.setPrio(prioridad);

        if (isEmpty()) {
            this.firstNode = node;
        }
        else {
            this.getLastNode().setNextNode(node);
            this.lastNode = node;
        }
        this.nodeCount++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }

        /*
        Iteramos entre los nodos,
        del primero al último para seguir FIFO,
        y sacamos el que tenga mayor prioridad (0 es mayor),
        y esté más próximo a la salida.
         */
        NodoPrioridad<T> iterator = this.firstNode;
        NodoPrioridad<T> nodeToDequeue = null;
        NodoPrioridad<T> prevNodeToDequeue = null;

        /*
        Variables auxiliares de apoyo, para restablecer punteros.
         */
        NodoPrioridad<T> auxPrev = null;


        while(null != iterator) {

            if(nodeToDequeue == null || iterator.getPrio() < nodeToDequeue.getPrio()) {
                prevNodeToDequeue = auxPrev;
                nodeToDequeue = iterator;
            }
            /* Avanzar cursor */
            auxPrev = iterator;
            iterator = iterator.getNextNode();
        }

        /* Si el elemento a decolar es el primero, ajustar */
        if(nodeToDequeue == this.firstNode) {
            this.firstNode = this.firstNode.getNextNode();
        }
        /*
        Ajustar punteros:
        El anterior al actual apuntará al posterior actual.
         */
        if(null != prevNodeToDequeue) {
            prevNodeToDequeue.setNextNode(nodeToDequeue.getNextNode());
        }

        this.nodeCount--;
        return nodeToDequeue.getElem();
    }

    private NodoPrioridad<T> getLastNode() {
        return this.getLastNode(this.firstNode);
    }

    private NodoPrioridad<T> getLastNode(NodoPrioridad<T> node) {
        if(node.getNextNode() != null) {
            return this.getLastNode(node.getNextNode());
        }
        else {
            return node;
        }
    }

    // For debugging purposes, ignore ;()
    public void showCurrentQueue() {
        if (isEmpty()) {
            System.out.println("DEBUG: EMPTY QUEUE!!!");
        }
        NodoPrioridad<T> iterador = this.firstNode;
        while (null != iterador && null != iterador.getElem()) {
            System.out.println(iterador);
            iterador = iterador.getNextNode();
        }
    }

    @Override
    public T front() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        return firstNode.getElem();
    }

    @Override
    public int getSize() {
        return this.nodeCount;
    }
}
