package com.github.raykkonerd.arvoresb.model;

import java.util.ArrayList;
import java.util.List;

public class Nodo implements Cloneable {
    private List<Chave> chaves;
    private Nodo pai;
    private boolean ehFolha;

    public Nodo() {
        this.chaves = new ArrayList<>();
        this.ehFolha = true;
    }

    public List<Chave> getChaves() {
        return this.chaves;
    }

    public void setChaves(List<Chave> chaves) {
        this.chaves = chaves;
    }

    public void adicionaChave(Chave chave) {
        List<Chave> chavesCopy = new ArrayList<>(this.chaves);
        chavesCopy.add(chave);
        chavesCopy.sort((c1, c2) -> Integer.compare(c1.getValor(), c2.getValor()));
        int chaveIndex = chavesCopy.indexOf(chave);
        if(chaveIndex > 0){
            chavesCopy.get(chaveIndex - 1).setFilhoADireita(chave.getFilhoAEsquerda());
        }
        if(chaveIndex < chavesCopy.size() - 1){
            chavesCopy.get(chaveIndex + 1).setFilhoAEsquerda(chave.getFilhoADireita());
        }
        this.chaves = chavesCopy;
    }

    public Nodo getPai() {
        return this.pai;
    }

    public void setPai(Nodo pai) {
        this.pai = pai;
    }

    public boolean getEhFolha() {
        return this.ehFolha;
    }

    public void setEhFolha(boolean ehFolha) {
        this.ehFolha = ehFolha;
    }

    @Override
    public Nodo clone(){
        Nodo novoNodo = new Nodo();
        novoNodo.setChaves(this.chaves);
        novoNodo.setEhFolha(this.ehFolha);
        novoNodo.setPai(this.pai);
        return novoNodo;
    }
}

