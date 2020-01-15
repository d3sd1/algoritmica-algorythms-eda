package Tema1.ProblemaEntrega;

public interface iBiblioteca {
    /* Extra: Definimos la constante de libros máxima */
    public int NUM_MAX_LIBROS = 1000;

    /* Inserta el libro al final de la biblioteca. Si el libro está repetido no lo inserta e imprime un mensaje. */
    public void insert(String titulo, String autor, Genero genero);

    /* Muestra por impresión de mensaje los libros buscando por su título. De no existir se imprime un mensaje de error. */
    public void searchBooksByTitle(String bookTitle);

    /* Devuelve los libros de un determinado autor. */
    public Libro[] searchBooksByAuthors(String author);

    /* Devuelve los libros de un determinado género. */
    public Libro[] searchBooksByGenre(Genero genre);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    Libro[] findBooksInLibrary(Libro libro);

    /* Extra: Esta función evita la redundancia en las búsquedas */
    Libro findBookInLibrary(Libro libro);
}
