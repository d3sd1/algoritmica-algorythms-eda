package Tema3.ProblemaEntrega;

import Tema3.ProblemaEntrega.Biblioteca.Biblioteca;
import Tema3.ProblemaEntrega.Biblioteca.Genero;
import Tema3.ProblemaEntrega.Biblioteca.Libro;
import Tema3.ProblemaEntrega.Lista.ListaSimple;
import Tema3.ProblemaEntrega.Lista.Node;

import java.util.Scanner;

public class Main {
    public static Biblioteca biblioteca;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        biblioteca = new Biblioteca();

        boolean keepMenu = true;
        while (keepMenu) {
            printMenu();
            pickOption();
        }
        input.close(); // Cerrando este escáner cerramos todos los demás.
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
        System.out.println();
    }

    public static void pickOption() {
        Scanner input = new Scanner(System.in);
        byte opt = 0;
        try {
            opt = input.nextByte();
            if (opt > 7 || opt < 0) { //Aceptamos las 7 opciones mostradas.
                throw new IndexOutOfBoundsException();
            }
        } catch (Exception e) {
            System.out.println("Por favor, introduce una opción válida.");
        }
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
        }
    }

    public static void autoFill() {
        ListaSimple<Libro> libros = new ListaSimple<Libro>();
        libros.addLast(new Libro("Hola mundo", "Andrei", Genero.ENSAYO));
        libros.addLast(new Libro("Otro libro", "Gonzalo", Genero.ENSAYO));
        libros.addLast(new Libro("Encontrando la vida", "Pepe", Genero.ENSAYO));
        libros.addLast(new Libro("Como jugar al LoL", "Mari carmen", Genero.ENSAYO));
        libros.addLast(new Libro("Vamos nino", "Juanola", Genero.ENSAYO));
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

        for (Node<Libro> nod = biblioteca.getLibros().getElems(); nod != null; nod = nod.getNext()) {
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
        try {
            Scanner input = new Scanner(System.in);
            String titulo = "", autor = "";

            System.out.println("Introduce el título del libro");
            titulo = input.nextLine();
            System.out.println("Introduce el autor del libro");
            autor = input.nextLine();

            Genero genero = pickGenre();

            biblioteca.insert(titulo, autor, genero);
        } catch (Exception e) {
            System.out.println("Ocurrió un añadir al insertar el lbro.");
        }
    }
}
