package com.github.raykkonerd.arvoresb.model;

import java.util.ArrayList;
import java.util.Arrays;

public class ArvoreB {
    private Nodo raiz;
    private int ordem;

    public ArvoreB(int ordem, int raiz) {
        this.ordem = ordem;
        this.raiz = new Nodo(ordem);
        this.raiz.adicionaChave(raiz);
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public void inserir(int valor) {
        if (this.raiz == null) {
            this.raiz = new Nodo(this.ordem);
            this.raiz.adicionaChave(valor);
            return;
        }
        inserir(this.raiz, valor);
    }

    public void inserir(Nodo nodo, int valor) {
        if (nodo.getEhFolha()) {
            for (int i = 0; i < nodo.getChaves().length - 1; i ++) {
                if (nodo.getChaves()[i] == null) {
                    nodo.adicionaChave(valor);
                    return;
                }
            }
            split(nodo, valor);
            return;
        }

        for (int i = 0; i < nodo.getChaves().length - 1; i++) {
            Integer chave = nodo.getChaves()[i];
            if (chave != null && valor < chave || i > 0 && nodo.getChaves()[i - 1] != null && chave == null) {
                if (nodo.getFilhos()[i] == null) {
                    Nodo novoNodo = new Nodo(this.ordem);
                    novoNodo.adicionaChave(valor);
                    novoNodo.setPai(nodo);
                    nodo.adicionaFilho(novoNodo);
                } else {
                    inserir(nodo.getFilhos()[i], valor);
                }
                return;
            }
        }

        Nodo ultimoFilho = nodo.getFilhos()[nodo.getFilhos().length - 2];
        if (ultimoFilho == null) {
            Nodo novoNodo = new Nodo(this.ordem);
            novoNodo.adicionaChave(valor);
            novoNodo.setPai(nodo);
            nodo.adicionaFilho(novoNodo);
        } else {
            inserir(ultimoFilho, valor);
        }
    }

    public void split(Nodo nodo, int valor) {
        nodo.adicionaChave(valor);
        ArrayList<Integer> listaComValor = new ArrayList<>(Arrays.asList(nodo.getChaves()));
        listaComValor.sort(Integer::compare);

        int indiceMeio = (listaComValor.size() % 2 == 0) ? (listaComValor.size() / 2 - 1) : (listaComValor.size() / 2);
        Integer chaveMeio = listaComValor.get(indiceMeio);

        Nodo metadeEsquerda = nodo.clone();
        metadeEsquerda.setChaves(listaComValor.subList(0, indiceMeio).toArray(new Integer[this.ordem]));
        Nodo[] filhosAEsquerda = Arrays.copyOfRange(nodo.getFilhos(), 0, indiceMeio + 2);
        metadeEsquerda.setFilhos(filhosAEsquerda);

        Nodo metadeDireita = nodo.clone();
        metadeDireita.setChaves(listaComValor.subList(indiceMeio + 1, listaComValor.size()).toArray(new Integer[this.ordem]));
        Nodo[] filhosADireita = Arrays.copyOfRange(nodo.getFilhos(), indiceMeio + 1, this.ordem * 2);
        metadeDireita.setFilhos(filhosADireita);

        if (nodo == this.raiz) {
            this.raiz = new Nodo(this.ordem);
            this.raiz.adicionaChave(chaveMeio);
            metadeEsquerda.setPai(this.raiz);
            this.raiz.adicionaFilho(metadeEsquerda);
            metadeDireita.setPai(this.raiz);
            this.raiz.adicionaFilho(metadeDireita);

            this.raiz.setEhFolha(false);
        } else {
            Nodo pai = nodo.getPai();
            pai.removeFilho(nodo);

            if(pai.getChaves()[pai.getChaves().length - 2] == null){
                pai.adicionaChave(chaveMeio);
                pai.adicionaFilho(metadeEsquerda);
                pai.adicionaFilho(metadeDireita);
            } else {
                pai.adicionaFilho(metadeEsquerda);
                pai.adicionaFilho(metadeDireita);
                split(pai, chaveMeio);
            }


        }
    }
}