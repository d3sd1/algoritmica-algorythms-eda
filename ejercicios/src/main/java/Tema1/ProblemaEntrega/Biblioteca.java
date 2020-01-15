package Tema1.ProblemaEntrega;

import java.util.Arrays;

public class Biblioteca implements iBiblioteca {

    private Libro[] libros;

    public Biblioteca(Libro[] libros) {
        this.libros = libros;
    }

    public Biblioteca() {
        this.libros = new Libro[this.NUM_MAX_LIBROS];
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
            boolean insertado = false;
            for (int i = 0; i < libros.length; i++) {
                if (libros[i] == null) {
                    libros[i] = nuevoLibro;
                    insertado = true;
                    break;
                }
            }

            if (insertado) {
                System.out.println("Libro insertado en la biblioteca correctamente.");
            }
            else {
                System.out.println("No se pudo insertar el libro debido a que la biblioteca está llena.");
            }
        }
    }

    /* Estos métodos evitan redundancias en todas las búsquedas */
    @Override
    public Libro findBookInLibrary(Libro libro) {
        Libro[] founds = this.findBooksInLibrary(libro);
        return founds[0]; // Ya que sólo interesa la primera ocurrencia.
    }

    @Override
    public Libro[] findBooksInLibrary(Libro libro) {
        /* Por defecto a longitud de libro, así nunca dará IndexOutOfBounds*/
        Libro[] resultados = new Libro[libros.length];
        int lastInsertedIndex = 0; // Variable de control de índice de resultados

        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null
                    && (
                    libros[i].getTitulo().toLowerCase().equals(libro.getTitulo().toLowerCase()) ||
                            libros[i].getGenero().equals(libro.getGenero()) ||
                            libros[i].getAutor().toLowerCase().equals(libro.getAutor().toLowerCase())
            )
            ) {
                resultados[lastInsertedIndex] = libros[i];
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

    private void printBooks(Libro[] books) {
        System.out.println("Libros encontrados: ");
        boolean noBooks = true;
        for(Libro libro:books) {
            if(null != libro) {
                noBooks = false;
                System.out.println(libro);
            }
        }

        if(noBooks) {
            System.out.println("Sin coincidencias.");
        }
    }

    @Override
    public Libro[] searchBooksByAuthors(String author) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el autor para buscar por el autor */
        searchBook.setAutor(author);

        Libro[] foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    @Override
    public Libro[] searchBooksByGenre(Genero genre) {
        Libro searchBook = new Libro();
        /* Se rellena sólo con el género y así no busca por el resto*/
        searchBook.setGenero(genre);

        Libro[] foundBooks = this.findBooksInLibrary(searchBook);
        this.printBooks(foundBooks);
        return foundBooks;
    }

    public Libro[] getLibros() {
        return libros;
    }

    public void setLibros(Libro[] libros) {
        this.libros = libros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biblioteca that = (Biblioteca) o;
        return Arrays.equals(libros, that.libros);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(libros);
    }

    @Override
    public String toString() {
        return "Libros en la biblioteca: " + Arrays.toString(libros);
    }
}
