package com.github.raykkonerd.arvoresb.model;

import java.util.List;

public class ArvoreB {
    private Nodo raiz;
    private int ordem;

    public ArvoreB(int ordem, int raiz) {
        this.ordem = ordem;
        this.raiz = new Nodo();
        Chave novaChave = new Chave(raiz);
        this.raiz.adicionaChave(novaChave);
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
            this.raiz = new Nodo();
            Chave novaChave = new Chave(valor);
            this.raiz.adicionaChave(novaChave);
            return;
        }
        inserir(this.raiz, valor);
    }

    public void inserir(Nodo nodo, int valor) {
        if (nodo.getEhFolha()) {
            Chave novaChave = new Chave(valor);
            if (nodo.getChaves().size() < this.ordem - 1) {
                nodo.adicionaChave(novaChave);
                return;
            }
            split(nodo, novaChave);
            return;
        }

        List<Chave> chaves = nodo.getChaves();
        for (int i = 0; i < chaves.size(); i++) {
            Chave chave = chaves.get(i);
            if (valor < chave.getValor()) {
                if (chave.getFilhoAEsquerda() == null) {
                    Nodo novoNodo = new Nodo();
                    Chave novaChave = new Chave(valor);
                    novoNodo.adicionaChave(novaChave);
                    novoNodo.setPai(nodo);
                    chave.setFilhoAEsquerda(novoNodo);
                } else {
                    inserir(chave.getFilhoAEsquerda(), valor);
                }
                return;
            }
        }

        Chave ultimaChave = nodo.getChaves().get(nodo.getChaves().size() - 1);
        Nodo ultimoFilho = ultimaChave.getFilhoADireita();
        if (ultimoFilho == null) {
            Nodo novoNodo = new Nodo();
            Chave novaChave = new Chave(valor);
            novoNodo.adicionaChave(novaChave);
            novoNodo.setPai(ultimoFilho);
            ultimaChave.setFilhoADireita(novoNodo);
        } else {
            inserir(ultimoFilho, valor);
        }
    }

    public void split(Nodo nodo, Chave novaChave) {
        nodo.adicionaChave(novaChave);
        
        List<Chave> chaves = nodo.getChaves();
        int indiceMeio = (chaves.size() % 2 == 0) ? chaves.size() / 2 - 1 : chaves.size() / 2;
        Chave chaveMeio = chaves.get(indiceMeio);
        // └── [20] (null)
        //     ├── [10] (20)
        //     │   ├── [7] (10)
        //     │   └── [11, 15] (10)
        //     └── [28, 30, 50] (20)
        //         ├── [26] (28)
        //         ├── [29] (28)
        //         └── [43, 50, 55] (28)
        // chaveMeio: 29 < 30 > 43
        // metadeEsquerda: [26 < 28 > 29] (28)
        // metadeDireita: [null < 55 > null] (28)

        Nodo metadeEsquerda = nodo.clone();
        metadeEsquerda.setChaves(chaves.subList(0, indiceMeio));
        Nodo metadeDireita = nodo.clone();
        metadeDireita.setChaves(chaves.subList(indiceMeio + 1, chaves.size()));

        for (Chave chave : metadeEsquerda.getChaves()) {
            if (chave.getFilhoAEsquerda() != null) {
                chave.getFilhoAEsquerda().setPai(metadeEsquerda);
            }
        }
        Chave ultimaChaveEsquerda = metadeEsquerda.getChaves().get(metadeEsquerda.getChaves().size() - 1);
        if (ultimaChaveEsquerda.getFilhoADireita() != null) {
            ultimaChaveEsquerda.getFilhoADireita().setPai(metadeEsquerda);
        }

        for (Chave chave : metadeDireita.getChaves()) {
            if (chave.getFilhoAEsquerda() != null) {
                chave.getFilhoAEsquerda().setPai(metadeDireita);
            }
        }
        Chave ultimaChaveDireita = metadeDireita.getChaves().get(metadeDireita.getChaves().size() - 1);
        if (ultimaChaveDireita.getFilhoADireita() != null) {
            ultimaChaveDireita.getFilhoADireita().setPai(metadeDireita);
        }

        chaveMeio.setFilhoAEsquerda(metadeEsquerda);
        chaveMeio.setFilhoADireita(metadeDireita);

        if (nodo == this.raiz) {
            this.raiz = new Nodo();
            metadeEsquerda.setPai(this.raiz);
            metadeDireita.setPai(this.raiz);
            this.raiz.adicionaChave(chaveMeio);
            this.raiz.setEhFolha(false);
        } else {
            Nodo pai = nodo.getPai();

            if (pai.getChaves().size() < this.ordem - 1) {
                pai.adicionaChave(chaveMeio);
                metadeEsquerda.setPai(pai);
                metadeDireita.setPai(pai);
            } else {
                split(pai, chaveMeio);
            }
        }
    }
}