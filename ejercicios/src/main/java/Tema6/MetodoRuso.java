package Tema6;

public class MetodoRuso {
    public static void main(String[] args) {
        int x = 146, y = 37;

        // Esto se debe comprobar fuera ya que sino no podría iterar hasta 1. Se podría hacer así o con un wrapper.
        if (x <= y) {
            System.out.println("El primer número debe ser mayor. " + x + y);
        }
        else {
            System.out.println("Resultado del método ruso: " + metodoRuso(x, y));
        }
    }

    // Lo hago static para simplificar el ejercicio aunque no sea lo óptimo
    private static int metodoRuso(int x, int y) { // FUNCIÓN RECURSIVA
        int newX = (int) Math.floor(x / 2), newY = y * 2;

        // Caso base
        if (newX <= 1) {
            System.out.println(newY);
            return newY;
        }

        // Caso iterativo doble
        if (newX % 2 != 0) { // Es impar. Sumar al número ruso
            System.out.println(newY);
            return newY + metodoRuso(newX, newY);
        }

        else { // Es par, continuar iteración sin sumar
            return metodoRuso(newX, newY);
        }
    }
}
