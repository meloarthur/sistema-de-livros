package domain;

import java.util.List;

public class Livro {
    private Integer id;
    private String titulo;
    private String isbn;
    private Integer edicao;
    private String autor;
    private String descricao;
    private List<Autor> autores;

    public Livro() {
    }
    
    public Livro(Integer id, String titulo, String isbn, Integer edicao, String autor, String descricao, List<Autor> autores) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.edicao = edicao;
        this.autor = autor;
        this.descricao = descricao;
        this.autores = autores;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Integer getEdicao() {
        return edicao;
    }
    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Autor = " + autor + "\nDescrição = " + descricao + "\nEdição = " + edicao + "\nId = " + id + "\nISBN = "
                + isbn + "\nTítulo = " + titulo + "\n\n";
    }
}
