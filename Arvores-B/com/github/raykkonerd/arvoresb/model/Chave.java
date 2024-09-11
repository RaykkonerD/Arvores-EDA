package com.github.raykkonerd.arvoresb.model;

public class Chave implements Comparable<Chave> {
    private Nodo filhoAEsquerda;
    private Nodo filhoADireita;
    private Integer valor;

    public Chave(Integer valor){
        this.valor = valor;
    }

    public Integer getValor(){
        return this.valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public Nodo getFilhoAEsquerda(){
        return this.filhoAEsquerda;
    }

    public void setFilhoAEsquerda(Nodo filhoAEsquerda){
        this.filhoAEsquerda = filhoAEsquerda;
    }

    public Nodo getFilhoADireita(){
        return this.filhoADireita;
    }

    public void setFilhoADireita(Nodo filhoADireita){
        this.filhoADireita = filhoADireita;
    }

    @Override
    public int compareTo(Chave obj) {
        Chave outro = (Chave)(obj);
        if(this.valor > outro.getValor()){
            return 1;
        } else if(this.valor == outro.getValor()){
            return 0;
        } else {
            return -1;
        }
    }
}
