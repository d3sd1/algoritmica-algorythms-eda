package Tema2.EjercicioEntregaA;


import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            generateIntegerCase();
            generateStringCase();
            generateMistakesCase();
        } catch(Exception e) {
            System.out.println("Algo ha salido mal, pero probando todo funciona genial y este mensaje nunca salió :)");
        }
    }

    public static void generateMistakesCase() {
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(0); //Sin prioridad
        Random rand = new Random();

        System.out.println("Procediendo a encolar 10 objetos.");
        for(int i = 0; i < 10; i++) {
            int prio = rand.nextInt(10);
            cola.enqueue(null, prio);
            System.out.println("Encolado " + i + " con prioridad " + prio);
        }

        //System.out.println("Tamaño de la cola: " + cola.getSize());
        //cola.showCurrentQueue();

        System.out.println("Procediendo a desencolar por FIFO con prioridad.");
        for(int i = 10; i > 0; i--) {
            Integer decolado = cola.dequeue();
            System.out.println("Decolado: " + decolado);
        }
    }

    public static void generateIntegerCase() {
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(10);
        Random rand = new Random();

        System.out.println("Procediendo a encolar 10 objetos.");
        for(int i = 0; i < 10; i++) {
            int prio = rand.nextInt(10);
            cola.enqueue(i, prio);
            System.out.println("Encolado " + i + " con prioridad " + prio);
        }

        //System.out.println("Tamaño de la cola: " + cola.getSize());
        //cola.showCurrentQueue();

        System.out.println("Procediendo a desencolar por FIFO con prioridad.");
        for(int i = 10; i > 0; i--) {
            Integer decolado = cola.dequeue();
            System.out.println("Decolado: " + decolado);
        }
    }

    public static void generateStringCase() {
        ColaPrioridad<String> cola = new ColaPrioridad<String>(10);
        Random rand = new Random();

        System.out.println("Procediendo a encolar 10 objetos.");
        for(int i = 0; i < 10; i++) {
            int prio = rand.nextInt(10);
            cola.enqueue(" Caso: " + i, prio);
            System.out.println("Encolado " + i + " con prioridad " + prio);
        }

        //System.out.println("Tamaño de la cola: " + cola.getSize());
        //cola.showCurrentQueue();

        System.out.println("Procediendo a desencolar por FIFO con prioridad.");
        for(int i = 10; i > 0; i--) {
            String decolado = cola.dequeue();
            System.out.println("Decolado: " + decolado);
        }
    }
}
