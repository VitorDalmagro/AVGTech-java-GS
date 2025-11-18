package br.com.avgtech.beans;

public class Curso {

    private int idCurso;
    private String nome;
    private String descricao;
    private String urlVideo;
    private String dataCriacao;

    public Curso() {
    }

    public Curso(int idCurso, String nome, String descricao, String urlVideo, String dataCriacao) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.descricao = descricao;
        this.urlVideo = urlVideo;
        this.dataCriacao = dataCriacao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
