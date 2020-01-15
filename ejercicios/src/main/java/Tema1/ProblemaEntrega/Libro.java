package Tema1.ProblemaEntrega;

public class Libro {
    private String titulo;
    private String autor;
    private Genero genero;

    public Libro() {
        this.setGenero(Genero.NOVELA);
        this.setAutor("");
        this.setTitulo("");
    }

    public Libro(String titulo, String autor, Genero genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return this.getTitulo() + ". Autor: " + this.getAutor() + " escribiendo del género " + this.getGenero();
    }
}
