package com.github.raykkonerd.arvoresbinairas.model;

import java.util.List;

public class Nodo {
    private int valor;
    private Nodo esquerda;
    private Nodo direita;
    private Nodo pai;
    
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

    public void setValor(int valor) {
        this.valor = valor;
    }
}