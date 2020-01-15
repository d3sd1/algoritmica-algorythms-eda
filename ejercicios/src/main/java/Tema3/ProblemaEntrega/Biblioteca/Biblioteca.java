package Tema3.ProblemaEntrega.Biblioteca;

import Tema3.ProblemaEntrega.Lista.ListaSimple;
import Tema3.ProblemaEntrega.Lista.Node;

import java.util.Arrays;

public class Biblioteca implements iBiblioteca {

    private ListaSimple<Libro> libros;

    public Biblioteca(ListaSimple<Libro> libros) {
        this.libros = libros;
    }

    public Biblioteca() {
        this.libros = new ListaSimple<Libro>();
    }

    @Override
    public void insert(String titulo, String autor, Genero genero) {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setTitulo(titulo);
        nuevoLibro.setAutor(autor);
        nuevoLibro.setGenero(genero);

        /* Comprobar que no exista en la biblioteca */
        if (null != this.findBookInLibrary(nuevoLibro)) {
            System.out.println("El libro ya estaba en la biblioteca, y por ende no se insertó.");
        } else {
            /* Insertar libro en la biblioteca */
            this.libros.addLast(nuevoLibro);
            System.out.println("Libro insertado en la biblioteca correctamente.");
        }
    }

    /* Estos métodos evitan redundancias en todas las búsquedas */
    @Override
    public Libro findBookInLibrary(Libro libro) {
        ListaSimple<Libro> founds = this.findBooksInLibrary(libro);
        return founds.getAt(0); // Ya que sólo interesa la primera ocurrencia.
    }

    @Override
    public ListaSimple<Libro> findBooksInLibrary(Libro libro) {
        ListaSimple<Libro> resultados = new ListaSimple<Libro>();
        for (Node<Libro> nod = this.libros.getElems(); nod != null; nod = nod.getNext()) {
            Libro lib = nod.getElem();
            if (lib != null
                    && (
                    lib.getTitulo().toLowerCase().equals(libro.getTitulo().toLowerCase()) ||
                            lib.getGenero().equals(libro.getGenero()) ||
                            lib.getAutor().toLowerCase().equals(libro.getAutor().toLowerCase())
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

        Libro foundBook = this.findBookInLibrary(searchBook);
        if (null != foundBook) {
            System.out.println("El libro " + foundBook.getTitulo() + ", su autor es " + foundBook.getAutor() + " y su género " + foundBook.getGenero() + ".");
        } else {
            System.out.println("El libro " + searchBook.getTitulo() + " no existe en la biblioteca.");
        }
    }

    private void printBooks(ListaSimple<Libro> books) {
        System.out.println("Libros encontrados: ");
        boolean noBooks = true;
        for (Node<Libro> nod = books.getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (null != libro) {
                noBooks = false;
                System.out.println(libro);
            }
        }

        if (noBooks) {
            System.out.println("Sin coincidencias.");
        }
    }

    @Override
    public ListaSimple<Libro> searchBooksByAuthors(String author) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el autor para buscar por el autor */
        searchBook.setAutor(author);

        ListaSimple<Libro> foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    @Override
    public ListaSimple<Libro> searchBooksByGenre(Genero genre) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el género y así no busca por el resto*/
        searchBook.setGenero(genre);

        ListaSimple<Libro> foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    @Override
    public void deleteBooksByGenre(Genero genre) {
        int i = 0;
        for (Node<Libro> nod = this.libros.getElems(); nod != null; nod = nod.getNext()) {
            Libro libro = nod.getElem();
            if (null != libro && libro.getGenero().equals(genre)) {
                this.libros.removeAt(i);
            }
            i++;
        }
    }

    public ListaSimple<Libro> getLibros() {
        return libros;
    }

    public void setLibros(ListaSimple<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Libros en la biblioteca: " + this.libros;
    }
}
