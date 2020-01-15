package Tema0;

import Utils.Random;

public class Matrix {
    int[][] matrix;
    public Matrix(int rows, int cols) {
        this.matrix = new int[rows][cols];
        //No hace falta recorrer el array para rellenar ya que int rellena por defecto a 0.
        // Para coger la longitud de las columnas podemos escoger cualquiera, ya que es regular.
    }

    public static Matrix createMatrix(int rows, int cols) {
        Matrix matrix = new Matrix(rows, cols);
        matrix.initMatrixRandom();
        return matrix;
    }

    private void initMatrixRandom() {
        Random rand = new Random();
        for(int i = 0; i < this.matrix.length; i++) {
            for(int j = 0; j < this.matrix[i].length; j++) {
                //this.matrix[i][j] = rand.generateRandIntRange(Constants.MIN_VAL_MATRIX,Constants.MAX_VAL_MATRIX);
            }
        }
    }

    public void show() {
        this.show(this.matrix);
    }

    public static void show(int[][] extMatrix) {
        System.out.println("------------------------");
        for(int i = 0; i < extMatrix.length; i++) {
            for(int j = 0; j < extMatrix[i].length; j++) {
                System.out.print(extMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    /*
    Definición de matriz traspuesta: Intercambio de filas por columnas.
     */
    public int[][] transpose() {
        // Se cambia la longitud del array estático en i / j por si no es una matriz cuadrada.
        //Cogemos el 0 como ejemplo para obtener la longitud, valdría cualquier otro índice.
        int[][] newMatrix = new int[this.matrix[0].length][this.matrix.length];
        for(int i = 0; i < this.matrix.length; i++) {
            for(int j = 0; j < this.matrix[i].length; j++) {
                newMatrix[j][i] = this.matrix[i][j];
            }
        }
        return newMatrix;
    }

    public int[][] plus(int[][] sumMatrix) {
        /*
        Por definición no se pueden sumar matrices de distinta dimensión.
        Tomamos 0 como ejemplo de vector fila para comprobar la longitud. Contamos que la matriz no es tan
        irregular como amorfa, y por ende todas sus filas tienen la misma dimensión.
         */
        if(sumMatrix == null) {
            return null;
        }
        int[][] finalSum = new int[this.matrix.length][this.matrix[0].length];

        for(int i = 0; i < this.matrix.length; i++) {
            // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos null.
            if(sumMatrix.length <= i) {
                return null;
            }
            for(int j = 0; j < this.matrix[i].length; j++) {
                // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos null.
                if(sumMatrix[i].length <= j) {
                    return null;
                }
                finalSum[i][j] = this.matrix[i][j] + sumMatrix[i][j];
            }
        }

        return finalSum;
    }


    public int[][] minus(int[][] minusMatrix) {
        /*
        Por definición no se pueden sumar matrices de distinta dimensión.
        Tomamos 0 como ejemplo de vector fila para comprobar la longitud. Contamos que la matriz no es tan
        irregular como amorfa, y por ende todas sus filas tienen la misma dimensión.
         */
        if(minusMatrix == null) {
            return null;
        }
        int[][] finalSum = new int[this.matrix.length][this.matrix[0].length];

        for(int i = 0; i < this.matrix.length; i++) {
            // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos null.
            if(minusMatrix.length <= i) {
                return null;
            }
            for(int j = 0; j < this.matrix[i].length; j++) {
                // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos null.
                if(minusMatrix[i].length <= j) {
                    return null;
                }
                finalSum[i][j] = this.matrix[i][j] - minusMatrix[i][j];
            }
        }

        return finalSum;
    }



    public boolean equal(int[][] equalMatrix) {
        /*
        Por definición no se pueden sumar matrices de distinta dimensión.
        Tomamos 0 como ejemplo de vector fila para comprobar la longitud. Contamos que la matriz no es tan
        irregular como amorfa, y por ende todas sus filas tienen la misma dimensión.
         */
        if(equalMatrix == null) {
            return false;
        }

        for(int i = 0; i < this.matrix.length; i++) {
            // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos false ya que no serán iguales.
            if(equalMatrix.length <= i) {
                return false;
            }
            for(int j = 0; j < this.matrix[i].length; j++) {
                // Comprobar que la matriz a sumar tiene el índice. Sino, no cumple la definición y retornamos false ya que no serán iguales.
                if(equalMatrix[i].length <= j) {
                    return false;
                }
                if(this.matrix[i][j] != equalMatrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

}
