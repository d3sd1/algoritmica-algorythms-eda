package Tema4.ProblemaEntrega.ProblemaEntrega.Biblioteca;

import Tema4.ProblemaEntrega.ProblemaEntrega.Lista.ListaDoblementeEnlazada;

public interface iBiblioteca {
    /* Inserta el libro al final de la biblioteca. Si el libro está repetido no lo inserta e imprime un mensaje. */
    void insert(String titulo, String autor, Genero genero);

    /* Muestra por impresión de mensaje los libros buscando por su título. De no existir se imprime un mensaje de error. */
    void searchBooksByTitle(String bookTitle);

    /* Devuelve los libros de un determinado autor. */
    ListaDoblementeEnlazada<Libro> searchBooksByAuthors(String author);

    /* Devuelve los libros de un determinado género. */
    ListaDoblementeEnlazada<Libro> searchBooksByGenre(Genero genre);

    /* Elimina los libros de un determinado género. */
    void deleteBooksByGenre(Genero genre);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    ListaDoblementeEnlazada<Libro> findBooksInLibrary(Libro libro);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    Libro findBookInLibrary(Libro libro);

    /* Devuelve el número de libros */
    int getNumBooks();

    /* Inserta un libro en la lista de manera ordenada tras reordenar la lista */
    void insertByTitle(String titulo, String autor, Genero genero);

    /* Muestra los libros de la biblioteca */
    void show();

    /* Ordenar colección por autor (ALF-ASC) */
    void orderByAuthor();

}
