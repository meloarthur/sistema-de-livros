package domain;

public class Autor {
    private Integer id;
    private String nome;
    private String nacionalidade;
    private Integer ano_nasc;
    
    public Autor() {
    }

    public Autor(Integer id, String nome, String nacionalidade, Integer ano_nasc) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.ano_nasc = ano_nasc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Integer getAno_nasc() {
        return ano_nasc;
    }

    public void setAno_nasc(Integer ano_nasc) {
        this.ano_nasc = ano_nasc;
    }

    @Override
    public String toString() {
        return "Autor [ano_nasc=" + ano_nasc + ", id=" + id + ", nacionalidade=" + nacionalidade + ", nome=" + nome
                + "]";
    }
}
