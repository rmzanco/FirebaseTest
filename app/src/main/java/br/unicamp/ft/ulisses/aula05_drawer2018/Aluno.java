package br.unicamp.ft.ulisses.aula05_drawer2018;

public class Aluno {
    private String nome;
    private String ra;
    private int foto;
    private String miniCV;
    private double altura;
    private int idade;
    private int time;

    public Aluno(String nome, String ra, int foto, String miniCV,
                 double altura, int idade, int time){
        this.nome  = nome;
        this.ra    = ra;
        this.foto  = foto;
        this.miniCV = miniCV;
        this.altura = altura;
        this.idade  = idade;
        this.time   = time;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getMiniCV() {
        return miniCV;
    }

    public void setMiniCV(String miniCV) {
        this.miniCV = miniCV;
    }


    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getAltura() {
        return altura;
    }

    public int getIdade() {
        return idade;
    }

    public int getTime() {
        return time;
    }
}
