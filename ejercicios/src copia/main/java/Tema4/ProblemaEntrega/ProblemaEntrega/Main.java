package Tema4.ProblemaEntrega.ProblemaEntrega;

import Tema4.ProblemaEntrega.ProblemaEntrega.Biblioteca.Biblioteca;
import Tema4.ProblemaEntrega.ProblemaEntrega.Biblioteca.Genero;
import Tema4.ProblemaEntrega.ProblemaEntrega.Biblioteca.Libro;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.ListaDoblementeEnlazada;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.Node;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.NodeDouble;

import java.util.Scanner;

public class Main {
    public static Biblioteca biblioteca;

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(System.in);
            biblioteca = new Biblioteca();

            boolean keepMenu = true;
            while (keepMenu) {
                printMenu();
                pickOption();
            }
            input.close(); // Cerrando este escáner cerramos todos los demás.

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error :S");
        }
    }

    public static void printMenu() {
        System.out.println("Selecciona una opción:");
        System.out.println();
        System.out.println("1. Insertar libro");
        System.out.println("2. Buscar libro por título");
        System.out.println("3. Buscar libro por autor");
        System.out.println("4. Buscar libro por género");
        System.out.println("5. Autorellenar");
        System.out.println("6. Ver biblioteca");
        System.out.println("7. Eliminar libro por género");
        System.out.println("8. Salir");
        System.out.println("---- BIS EJERCICIO 4 ----");
        System.out.println("9. Mostrar (mediante show)");
        System.out.println("10. Devolver el número de libros");
        System.out.println("11. Ordenar por autor");
        System.out.println("12. Insertar de manera ordenada por título");
        System.out.println();
    }

    public static void pickOption() {
        Scanner input = new Scanner(System.in);
        byte opt = 0;
        try {
            opt = input.nextByte();
            switch (opt) {
                case 1:
                    addBookManager();
                    break;
                case 2:
                    searchBookManager("title");
                    break;
                case 3:
                    searchBookManager("author");
                    break;
                case 4:
                    searchBookManager("genre");
                    break;
                case 5:
                    autoFill();
                    break;
                case 6:
                    showLibrary();
                    break;
                case 7:
                    removeBookManager("genre");
                    break;
                case 8:
                    System.out.println("¡Hasta luego!");
                    System.exit(1);
                    break;
                case 9:
                    biblioteca.show();
                    break;
                case 10:
                    System.out.println(biblioteca.getNumBooks());
                    break;
                case 11:
                    biblioteca.orderByAuthor();
                    break;
                case 12:
                    addBookManager(true);
                    break;
                default:
                    System.out.println("Por favor, introduce una opción válida.");
                    throw new IndexOutOfBoundsException();
            }
        } catch (Exception e) {
        }
    }

    public static void autoFill() {
        ListaDoblementeEnlazada<Libro> libros = new ListaDoblementeEnlazada<Libro>();
        libros.addLast(new Libro("Otro libro", "Gonzalo", Genero.ENSAYO));
        libros.addLast(new Libro("Encontrando la vida", "Pepe", Genero.ENSAYO));
        libros.addLast(new Libro("Como jugar al LoL", "Mari carmen", Genero.ENSAYO));
        libros.addLast(new Libro("Vamos nino", "Juanola", Genero.ENSAYO));
        libros.addLast(new Libro("Hola mundo", "Andrei", Genero.ENSAYO));
        biblioteca.setLibros(libros);
        System.out.println("Datos rellenados correctamente.");
    }

    public static void searchBookManager(String paramToSearch) {
        Scanner input = new Scanner(System.in);
        String searchVal = "";
        // Se usa if/else para retrocompatibilidad con Java 7-
        try {
            if (paramToSearch.equals("title")) {
                System.out.println("Introduce el título del libro a buscar");
                searchVal = input.nextLine();
                biblioteca.searchBooksByTitle(searchVal);
            } else if (paramToSearch.equals("author")) {
                System.out.println("Introduce el autor del libro a buscar");
                searchVal = input.nextLine();
                biblioteca.searchBooksByAuthors(searchVal);
            } else if (paramToSearch.equals("genre")) {
                biblioteca.searchBooksByGenre(pickGenre());
            }
        } catch (Exception e) {
            System.out.println("Parámetro de búsqueda incorrecto.");
        }
    }

    public static void removeBookManager(String paramToSearch) {
        Scanner input = new Scanner(System.in);
        String searchVal = "";
        // Se usa if/else para retrocompatibilidad con Java 7-
        try {
            if (paramToSearch.equals("genre")) {
                biblioteca.deleteBooksByGenre(pickGenre());
            }
        } catch (Exception e) {
            System.out.println("Parámetro de búsqueda incorrecto.");
        }
    }

    public static Genero pickGenre() {
        Genero genero = Genero.NOVELA;
        Scanner input = new Scanner(System.in);
        System.out.println("Selecciona el género del libro (Por defecto Novela):");
        System.out.println("1. Novela");
        System.out.println("2. Historia");
        System.out.println("3. Ensayo");
        try {
            switch (input.nextShort()) {
                case 1:
                    genero = Genero.NOVELA;
                    break;
                case 2:
                    genero = Genero.HISTORIA;
                    break;
                case 3:
                    genero = Genero.ENSAYO;
                    break;
            }

        } catch (Exception e) {
            System.out.println("El género seleccionado no existe. Se usará el por defecto.");
        }
        return genero;
    }

    public static void showLibrary() {
        boolean emptyLibrary = true;

        for (NodeDouble<Libro> nod = biblioteca.getLibros().getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (null != libro) {
                System.out.println(libro);
                emptyLibrary = false;
            }
        }

        if (emptyLibrary) {
            System.out.println("Biblioteca vacía.");
        }
    }

    public static void addBookManager() {
        addBookManager(false);
    }

    public static void addBookManager(boolean byTitle) {
        try {
            Scanner input = new Scanner(System.in);
            String titulo = "", autor = "";

            System.out.println("Introduce el título del libro");
            titulo = input.nextLine();
            System.out.println("Introduce el autor del libro");
            autor = input.nextLine();

            Genero genero = pickGenre();

            if (!byTitle) {
                biblioteca.insert(titulo, autor, genero);
            } else {
                biblioteca.insertByTitle(titulo, autor, genero);
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un añadir al insertar el libro.");
        }
    }
}
