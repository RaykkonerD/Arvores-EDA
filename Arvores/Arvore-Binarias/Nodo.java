public class Nodo {
    private int valor;
    private Nodo esquerda;
    private Nodo direita;

    public Nodo(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return this.valor;
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