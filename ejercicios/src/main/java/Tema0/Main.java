package Tema0;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        ¿Cómo	se	almacena	una	matriz	MxN	en	la	memoria?
            Como un objeto.

        ¿Qué	atributos	se	necesitan y de	qué	tipos	son	(estáticos	o	no	estáticos)?
            No estáticos los pertenecientes al objeto íntegramente, estáticos los comunes a todos y teniendo cuidado de cara a la optimización.
         */
        int rows, cols;
        Scanner input = new Scanner(System.in);

        System.out.println("Introduce el número de filas: ");
        rows = input.nextInt();
        System.out.println("Introduce el número de columnas: ");
        cols = input.nextInt();

        Matrix matrix = Matrix.createMatrix(rows,cols);
        matrix.show();

        Matrix.show(matrix.transpose());

        input.close();
    }
}
