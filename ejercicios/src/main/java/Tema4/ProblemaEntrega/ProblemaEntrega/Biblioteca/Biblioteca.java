package Tema4.ProblemaEntrega.ProblemaEntrega.Biblioteca;

import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.ListaDoblementeEnlazada;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.ListaDoblementeEnlazada;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.Node;
import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.NodeDouble;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Biblioteca implements iBiblioteca {

    private ListaDoblementeEnlazada<Libro> libros;

    public Biblioteca(ListaDoblementeEnlazada<Libro> libros) {
        this.libros = libros;
    }

    public Biblioteca() {
        this.libros = new ListaDoblementeEnlazada<Libro>();
    }

    @Override
    public void insert(String titulo, String autor, Genero genero) {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setGenero(null);

        /* Comprobar que no exista en la biblioteca */
        if (null != this.findBookInLibrary(nuevoLibro)) {
            System.out.println("El libro ya estaba en la biblioteca, y por ende no se insertó.");
        } else {
            nuevoLibro.setAutor(autor);
            nuevoLibro.setGenero(genero);
            /* Insertar libro en la biblioteca */
            this.libros.addLast(nuevoLibro);
            System.out.println("Libro insertado en la biblioteca correctamente.");
        }
    }

    /* Estos métodos evitan redundancias en todas las búsquedas */
    @Override
    public Libro findBookInLibrary(Libro libro) {
        ListaDoblementeEnlazada<Libro> founds = this.findBooksInLibrary(libro);
        Libro returnedBook = null;
        if (founds.getSize() > 0) {
            returnedBook = founds.getAt(0);
        }
        return returnedBook; // Ya que sólo interesa la primera ocurrencia.
    }

    @Override
    public ListaDoblementeEnlazada<Libro> findBooksInLibrary(Libro libro) {
        ListaDoblementeEnlazada<Libro> resultados = new ListaDoblementeEnlazada<Libro>();
        for (NodeDouble<Libro> nod = this.libros.getElems(); nod != null; nod = nod.getNext()) {
            Libro lib = nod.getElem();
            if (lib != null
                    && (
                    (libro.getTitulo() != null && lib.getTitulo().toLowerCase().equals(libro.getTitulo().toLowerCase())) ||
                            (libro.getGenero() != null && lib.getGenero().equals(libro.getGenero())) ||
                            (libro.getAutor() != null && lib.getAutor().toLowerCase().equals(libro.getAutor().toLowerCase()))
            )
            ) {
                resultados.addLast(lib);
            }
        }

        return resultados;
    }

    @Override
    public void searchBooksByTitle(String bookTitle) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el título para buscar por título */
        searchBook.setTitulo(bookTitle);
        searchBook.setGenero(null);

        Libro foundBook = this.findBookInLibrary(searchBook);
        if (null != foundBook) {
            System.out.println("El libro " + foundBook.getTitulo() + ", su autor es " + foundBook.getAutor() + " y su género " + foundBook.getGenero() + ".");
        } else {
            System.out.println("El libro " + searchBook.getTitulo() + " no existe en la biblioteca.");
        }
    }

    private void printBooks(ListaDoblementeEnlazada<Libro> books) {
        System.out.println("Libros encontrados: ");
        boolean noBooks = true;
        for (NodeDouble<Libro> nod = books.getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (null != libro) {
                noBooks = false;
            }
        }

        if (noBooks) {
            System.out.println("Sin coincidencias.");
        }
    }

    @Override
    public ListaDoblementeEnlazada<Libro> searchBooksByAuthors(String author) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el autor para buscar por el autor */
        searchBook.setAutor(author);

        ListaDoblementeEnlazada<Libro> foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    @Override
    public ListaDoblementeEnlazada<Libro> searchBooksByGenre(Genero genre) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el género y así no busca por el resto*/
        searchBook.setGenero(genre);

        ListaDoblementeEnlazada<Libro> foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    @Override
    public void deleteBooksByGenre(Genero genre) {
        int i = 0;
        for (NodeDouble<Libro> nod = this.libros.getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (null != libro && libro.getGenero().equals(genre)) {
                this.libros.removeAt(i);
            }
            i++;
        }
    }

    public ListaDoblementeEnlazada<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ListaDoblementeEnlazada<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Libros en la biblioteca: " + this.libros;
    }

    @Override
    public int getNumBooks() {
        return this.libros.getSize();
    }

    @Override
    public void insertByTitle(String titulo, String autor, Genero genero) {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setGenero(null);

        /* Comprobar que no exista en la biblioteca */
        if (null != this.findBookInLibrary(nuevoLibro)) {
            System.out.println("El libro ya estaba en la biblioteca, y por ende no se insertó.");
        } else {
            /* Insertar libro en la biblioteca */
            nuevoLibro.setAutor(autor);
            nuevoLibro.setGenero(genero);
            this.insert(titulo, autor, genero);
            this.orderByTitle();
            System.out.println("Libro insertado en la biblioteca correctamente (de manera ordenada).");
        }
    }

    @Override
    public void show() {
        for (NodeDouble<Libro> nod = this.libros.getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (nod.getElem() != null) {
                System.out.println(libro);
            }
        }
    }


    @Override
    public void orderByAuthor() {
        for (NodeDouble<Libro> i = this.libros.getElems(); i != null; i = i.getNext()) {
            for (NodeDouble<Libro> j = this.libros.getElems(); j != null; j = j.getNext()) {
                if (j.getElem() != null && j.getPrev() != null && j.getPrev().getElem() != null &&
                        j.getPrev().getElem().getAutor().compareTo(j.getElem().getAutor()) > 0) {
                    NodeDouble<Libro> prev = j.getPrev();
                    NodeDouble<Libro> actual = j;
                    NodeDouble<Libro> next = j.getNext();

                    actual.setNext(prev);
                    prev.setNext(next);
                    next.setPrev(prev);

                    prev.getPrev().setNext(actual);
                    actual.setPrev(prev.getPrev());
                    prev.setPrev(actual);

                }
            }
        }
    }


    public void orderByTitle() {
        for (NodeDouble<Libro> i = this.libros.getElems(); i != null; i = i.getNext()) {
            for (NodeDouble<Libro> j = this.libros.getElems(); j != null; j = j.getNext()) {
                if (j.getElem() != null && j.getPrev() != null && j.getPrev().getElem() != null &&
                        j.getPrev().getElem().getTitulo().compareTo(j.getElem().getTitulo()) > 0) {
                    NodeDouble<Libro> prev = j.getPrev();
                    NodeDouble<Libro> actual = j;
                    NodeDouble<Libro> next = j.getNext();

                    actual.setNext(prev);
                    prev.setNext(next);
                    next.setPrev(prev);

                    prev.getPrev().setNext(actual);
                    actual.setPrev(prev.getPrev());
                    prev.setPrev(actual);

                }
            }
        }
    }
}
