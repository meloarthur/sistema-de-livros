package domain;

public class LivroAutor {

    private Integer idLivro;
    private Integer idAutor;
    
    public LivroAutor(Integer idLivro, Integer idAutor) {
        this.idLivro = idLivro;
        this.idAutor = idAutor;
    }

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }
    
}
