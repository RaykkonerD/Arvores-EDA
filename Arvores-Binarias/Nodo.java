
public class Nodo {
    private int valor;
    private Nodo pai;
    private Nodo esquerda;
    private Nodo direita;
    private int altura;

    public Nodo(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
    }

    public Nodo getPai(){
        return this.pai;
    }

    public void setPai(Nodo pai){
        this.pai = pai;
    }

    public int getAltura(){
        return this.altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public Nodo getEsquerda(){
        return this.esquerda;
    }

    public void setEsquerda(Nodo esquerda){
        this.esquerda = esquerda;
    }

    public Nodo getDireita(){
        return this.direita;
    }

    public void setDireita(Nodo direita){
        this.direita = direita;
    }
}