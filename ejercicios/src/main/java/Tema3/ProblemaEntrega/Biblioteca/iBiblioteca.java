package Tema3.ProblemaEntrega.Biblioteca;

import Tema3.ProblemaEntrega.Lista.ListaSimple;

public interface iBiblioteca {
    /* Inserta el libro al final de la biblioteca. Si el libro está repetido no lo inserta e imprime un mensaje. */
    void insert(String titulo, String autor, Genero genero);

    /* Muestra por impresión de mensaje los libros buscando por su título. De no existir se imprime un mensaje de error. */
    void searchBooksByTitle(String bookTitle);

    /* Devuelve los libros de un determinado autor. */
    ListaSimple<Libro> searchBooksByAuthors(String author);

    /* Devuelve los libros de un determinado género. */
    ListaSimple<Libro> searchBooksByGenre(Genero genre);

    /* Elimina los libros de un determinado género. */
    void deleteBooksByGenre(Genero genre);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    ListaSimple<Libro> findBooksInLibrary(Libro libro);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    Libro findBookInLibrary(Libro libro);
}
