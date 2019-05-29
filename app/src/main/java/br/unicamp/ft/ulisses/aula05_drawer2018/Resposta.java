package br.unicamp.ft.ulisses.aula05_drawer2018;

public class Resposta {
    private String name;
    private String answer;
    private String chosen;

    public Resposta(){
    }

    public Resposta(String name, String answer, String chosen) {
        this.name = name;
        this.answer = answer;
        this.chosen = chosen;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChosen() {
        return chosen;
    }

    public void setChosen(String chosen) {
        this.chosen = chosen;
    }
}
