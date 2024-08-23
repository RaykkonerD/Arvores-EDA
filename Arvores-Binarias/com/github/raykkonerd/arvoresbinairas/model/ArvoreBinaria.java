package com.github.raykkonerd.arvoresbinairas.model;

import java.lang.Math;

public class ArvoreBinaria {
    private Nodo raiz;

    public ArvoreBinaria(int raiz) {
        this.raiz = new Nodo(raiz);
    }

    public Nodo getRaiz(){
        return this.raiz;
    }

    public void setRaiz(Nodo raiz){
        this.raiz = raiz;
    }

    public int calcularAltura() {
        return calcularAltura(this.raiz);
    }

    protected int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }

        return Math.max(calcularAltura(nodo.getEsquerda()), calcularAltura(nodo.getDireita())) + 1;
    }

    public void inserir(int valor) {
        if (this.raiz == null) {
            this.raiz = new Nodo(valor);
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            inserir(novoNodo, this.raiz);
        }
    }

    protected void inserir(Nodo novoNodo, Nodo pai) {
        if (novoNodo.getValor() < pai.getValor()) {
            if (pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                return;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if (pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                return;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }

    public boolean removerUltimo() {
        if (this.raiz == null) {
            return false;
        }

        if (this.raiz.getDireita() == null) {
            Nodo novaRaiz = this.raiz.getEsquerda();
            this.raiz = novaRaiz;
            return true;
        }

        Nodo pai = this.raiz;
        removerUltimo(pai, pai.getDireita());
        return true;
    }

    protected void removerUltimo(Nodo pai, Nodo atual) {
        if (atual.getDireita() == null) {
            Nodo novoUltimo = atual.getEsquerda();
            pai.setDireita(novoUltimo);
            return ;
        }

        removerUltimo(atual, atual.getDireita());
    }

    public boolean removerInicio() {
        if (this.raiz == null) {
            return false;
        }

        if (this.raiz.getEsquerda() == null) {
            Nodo novaRaiz = this.raiz.getDireita();
            this.raiz = novaRaiz;
            return true;
        }

        Nodo pai = this.raiz;
        removerInicio(pai, pai.getEsquerda());
        return true;
    }

    protected void removerInicio(Nodo pai, Nodo atual) {
        if (atual.getEsquerda() == null) {
            Nodo novoInicio = atual.getDireita();
            pai.setEsquerda(novoInicio);
            return ;
        }

        removerInicio(atual, atual.getEsquerda());
    }

    public Nodo getMenor(){
        return getMaior(this.raiz);
    }

    protected Nodo getMenor(Nodo nodo){
        if(nodo == null){
            return null;
        }

        if(nodo.getEsquerda() == null){
            return nodo;
        }

        return getMenor(nodo.getEsquerda());
    }

    public Nodo getMaior(){
        return getMaior(this.raiz);
    }

    protected Nodo getMaior(Nodo nodo){
        if(nodo == null){
            return null;
        }
        
        if(nodo.getDireita() == null){
            return nodo;
        }

        return getMaior(nodo.getDireita());
    }

    public boolean buscar(int valor) {
        return buscar(this.raiz, valor);
    }

    protected boolean buscar(Nodo pai, int valor) {
        if (pai == null) {
            return false;
        } else if (valor < pai.getValor()) {
            return buscar(pai.getEsquerda(), valor);
        } else if (valor > pai.getValor()) {
            return buscar(pai.getDireita(), valor);
        }

        return true;
    }

    public void percorrerPreOrdem() {
        System.out.print("Pré-ordem: ");
        percorrerPreOrdem(this.raiz);
        System.out.println("\n");
    }

    protected void percorrerPreOrdem(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            percorrerPreOrdem(nodo.getEsquerda());
            percorrerPreOrdem(nodo.getDireita());
        }
    }

    public void percorrerEmOrdem() {
        System.out.print("Em ordem: ");
        percorrerEmOrdem(this.raiz);
        System.out.println("\n");
    }

    protected void percorrerEmOrdem(Nodo nodo) {
        if (nodo != null) {
            percorrerEmOrdem(nodo.getEsquerda());
            System.out.print(nodo.getValor() + " ");
            percorrerEmOrdem(nodo.getDireita());
        }
    }

    public void percorrerPosOrdem() {
        System.out.print("Pós-ordem: ");
        percorrerPosOrdem(this.raiz);
        System.out.println("\n");
    }

    protected void percorrerPosOrdem(Nodo nodo) {
        if (nodo != null) {
            percorrerPosOrdem(nodo.getEsquerda());
            percorrerPosOrdem(nodo.getDireita());
            System.out.print(nodo.getValor() + " ");
        }
    }

    public void imprimir() {
        imprimirRecursivo(this.getRaiz(), "", true);
    }

    protected void imprimirRecursivo(Nodo nodo, String prefixo, boolean ehUltimo) {
        if (nodo != null) {
            System.out.print(prefixo);
            System.out.print(ehUltimo ? "└── " : "├── ");
            System.out.println(nodo.getValor());
            imprimirRecursivo(nodo.getDireita(), prefixo + (ehUltimo ? "    " : "│   "), false);
            imprimirRecursivo(nodo.getEsquerda(), prefixo + (ehUltimo ? "    " : "│   "), true);
        }
    }
}
