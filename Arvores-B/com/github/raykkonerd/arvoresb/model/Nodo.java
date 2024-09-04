package com.github.raykkonerd.arvoresb.model;

public class Nodo implements Cloneable {
    private Integer[] chaves;
    private Nodo[] filhos;
    private Nodo pai;
    private boolean ehFolha;

    public Nodo(int nFilhos) {
        this.chaves = new Integer[nFilhos];
        this.filhos = new Nodo[nFilhos + 1];
        this.ehFolha = true;
    }

    public Integer[] getChaves() {
        return this.chaves;
    }

    public void setChaves(Integer[] chaves) {
        this.chaves = chaves;
    }

    public void adicionaChave(Integer valor) {
        this.chaves[this.chaves.length - 1] = valor;

        for (int i = 1; i < this.chaves.length; i++) {
            for (int j = 0; j < i; j++) {
                if (this.chaves[i] != null) {
                    if (this.chaves[j] == null || this.chaves[i] < this.chaves[j]) {
                        Integer temp = this.chaves[i];
                        this.chaves[i] = this.chaves[j];
                        this.chaves[j] = temp;
                    }
                }
            }
        }
    }

    public void adicionaFilho(Nodo nodo) {
        for (int i = 0; i < this.chaves.length; i++) {
            if (this.chaves[i] != null) {
                if ((i == 0 && nodo.getChaves()[0] < this.chaves[i])
                        || (i > 0 && nodo.getChaves()[0] > this.chaves[i - 1] && nodo.getChaves()[0] < this.chaves[i])) {
                    this.filhos[i] = nodo;
                } else if (i == this.chaves.length - 1 && nodo.getChaves()[0] > this.chaves[i]) {
                    this.filhos[i + 1] = nodo;
                }
            } else if(this.chaves[i-1] != null){
                if(i > 0 && nodo.getChaves()[0] > this.chaves[i-1]){
                    this.filhos[i] = nodo;
                }
            }
        }
    }

    public void removeFilho(Nodo nodo){
        for (int i = 0; i < this.filhos.length - 1; i++) {
            if (this.filhos[i] == nodo) {
                this.filhos[i] = null;
                break;
            }
        }
    }

    public Nodo[] getFilhos() {
        return this.filhos;
    }

    public void setFilhos(Nodo[] filhos) {
        this.filhos = filhos;
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
        Nodo novoNodo = new Nodo(this.filhos.length);
        novoNodo.setChaves(this.chaves);
        novoNodo.setFilhos(this.filhos);
        novoNodo.setEhFolha(this.ehFolha);
        novoNodo.setPai(this.pai);
        return novoNodo;
    }
}

