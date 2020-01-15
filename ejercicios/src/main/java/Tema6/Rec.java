package Tema6;

import java.util.Arrays;

public class Rec {
    public static int factorial(int n) {
        if (n <= 0) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

    // Problema 1 - Escribir un método recursivo que devuelve la
// suma de los N primeros enteros.
    public static int suma(int n) {
        if (n == 0) {
            return 0;
        } else {
            return suma(n - 1) + n;
        }
    }

    // Problema 3 – Escribir un método recursivo que tome un número
// entero y devuelva el número de sus dígitos (por ejemplo, 2356
// tiene 4 dígitos). Pista: 2356/10 = 235 , 235/10=23 y 23/10= 2
// y no puedo seguir dividiendo entre 10
    public static int numDigitsIt(int n) {
        int result = 1;
        while (n >= 10) {
            result++;
            n = n / 10;
        }
        return result;
    }

    public static int numDigitsRec(int n) {
        if (n < 10) {
            return 1;
        } else {
            return 1 + numDigitsRec(n / 10);
        }
    }

    // Búsqueda binaria recursiva
    public static boolean binarySearch(int[] array, int n) {
        if (array.length == 0) {
            return false;
        } else {
            int i = array.length / 2;
            if (array[i] == n) {
                return true;
            }
            if (n < array[i]) {
                return binarySearch(Arrays.copyOfRange(array, 0, i), n);
            } else {
                return binarySearch(Arrays.copyOfRange(array, i + 1, array.length),
                        n);
            }
        }
    }

    // Problema 5 – Escribir un método recursivo que tome un
// array de enteros y compruebe si el array está ordenado
// (orden ascendente).
    public static boolean isSorted(int[] array) {
        return isSorted(array, array.length);
    }

    private static boolean isSorted(int[] array, int length) {
        if (length <= 1) {
            return true;
        }
        if (array[array.length - 2] > array[array.length - 1]) {
            return false;
        }
        return isSorted(array, length - 1);
    }

    // Problema 6 – Escribir un método recursivo que toma un array
// de enteros y devuelve el elemento más pequeño.
    public static int smallest(int[] array) {
        return smallest(array, 0, array.length);
    }

    public static int smallest(int[] array, int from, int to) {
        if (from == to) {
            return Integer.MAX_VALUE;
        }
        if (from == to - 1) {
            return array[from];
        }
        int i = (from + to) / 2;
        return Math.min(smallest(array, from, i), smallest(array, i, to));
    }

    // gr. 80
    public static int smallest_gr80(int[] array) {
        if (array.length == 0) {
            return Integer.MAX_VALUE;
        }
        if (array.length == 1) {
            return (array[0]);
        }
        return Math.min(smallest(Arrays.copyOf(array, array.length - 1)),
                array[array.length - 1]);
    }

    // Escribir un método que implemente la función de Ackerman.
// La función de Ackermann se puede definir de la siguiente manera:
// A(0, y) = y + 1
// A(x, 0) = A(x - 1, 1)
// A(x, y) = A(x - 1, A(x, y - 1))
    public static int A(int x, int y) {
        if (x == 0) {
            return y + 1;
        }
        if (y == 0) {
            return A(x - 1, 1);
        }
        return A(x - 1, A(x, y - 1));
    }

    // Parte 1
    public static int numOccurences(int numSearched, int[] arr) {
        if (arr.length < 1) {
            return 0;
        }
        int actualNum = arr[0];
        // QUITAR PRIMER INDICE DEL ARRAY
        arr = Arrays.copyOfRange(arr, 1, arr.length);
        if(actualNum == numSearched) {
            return 1 + numOccurences(numSearched, arr);
        }

        return numOccurences(numSearched, arr);
    }
    // Parte 2
    public static int indexOf(int numSearched, int[] arr) {
        if (arr.length < 1) {
            return -1;
        }
        int actualNum = arr[0];
        // QUITAR PRIMER INDICE DEL ARRAY
        arr = Arrays.copyOfRange(arr, 1, arr.length);
        if(actualNum == numSearched) {
            return 0;
        }

        int nextIteration = indexOf(numSearched, arr);
        if(nextIteration == -1) { //NO RESULTS
            return -1;
        }
        return 1 + nextIteration;
    }

    public static void main(String[] args) {
        int[] array = {3, 8, 2, 5, 9, 7};
        System.out.println(smallest(array));
        System.out.println("Número de treses en el array: " + numOccurences(3, array));
        System.out.println("Índice del número 8: " + indexOf(8, array));
    }
}