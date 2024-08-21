package com.github.raykkonerd.model;

import java.lang.Math;

import com.github.raykkonerd.util.ApresentaArvore;

public class ArvoreAVL extends ArvoreBinaria {
    public ArvoreAVL(int raiz) {
        super(raiz);
    }

    public int calcularAltura(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        return Math.max(calcularAltura(nodo.getEsquerda()), calcularAltura(nodo.getDireita())) + 1;
    }

    public int calcularFatorDeBalanceamento(Nodo nodo) {
        if (nodo != null) {
            int fatorDeBalanceamentoEsquerda = calcularFatorDeBalanceamento(nodo.getEsquerda());
            int fatorDeBalanceamentoDireita = calcularFatorDeBalanceamento(nodo.getDireita());
            int alturaEsquerda = calcularAltura(nodo.getEsquerda());
            int alturaDireita = calcularAltura(nodo.getDireita());
            int fatorDeBalanceamento = alturaEsquerda - alturaDireita;

            if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == 1) {
                // Rotação simples à direita
                rotacaoADireita(nodo);
            } else if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == -1) {
                // Rotação dupla à esquerda/direita
                rotacaoAEsquerda(nodo.getEsquerda());
                rotacaoADireita(nodo);
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoDireita == -1) {
                // Rotação simples à esquerda
                rotacaoAEsquerda(nodo);
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoDireita == 1) {
                // Rotação dupla à direita/esquerda
                rotacaoADireita(nodo.getDireita());
                rotacaoAEsquerda(nodo);
            }

            return fatorDeBalanceamento;
        } else {
            return -1;
        }
    }

    public void rotacaoADireita(Nodo nodo) {
        if (super.getRaiz() == nodo) {
            super.setRaiz(nodo.getEsquerda());
            super.getRaiz().setPai(null);
            Nodo aDireita = super.getRaiz().getDireita();

            if (aDireita != null) {
                aDireita.setPai(nodo);
            }

            nodo.setEsquerda(aDireita);
            nodo.setPai(super.getRaiz());
            super.getRaiz().setDireita(nodo);
        } else {
            nodo.getEsquerda().setPai(nodo.getPai());
            if(nodo.getPai().getEsquerda() == nodo){
                nodo.getPai().setEsquerda(nodo.getEsquerda());
            } else {
                nodo.getPai().setDireita(nodo.getEsquerda());
            }
            nodo.setPai(nodo.getEsquerda());
            nodo.setEsquerda(nodo.getEsquerda().getDireita());
            nodo.getPai().setDireita(nodo);
        }
    }

    public void rotacaoAEsquerda(Nodo nodo) {
        if (super.getRaiz() == nodo) {
            super.setRaiz(nodo.getDireita());
            super.getRaiz().setPai(null);
            Nodo aEsquerda = super.getRaiz().getEsquerda();

            if (aEsquerda != null) {
                aEsquerda.setPai(nodo);
            }

            nodo.setDireita(aEsquerda);
            nodo.setPai(super.getRaiz());
            super.getRaiz().setEsquerda(nodo);
        } else {
            nodo.getDireita().setPai(nodo.getPai());
            if(nodo.getPai().getDireita() == nodo){
                nodo.getPai().setDireita(nodo.getDireita());
            } else {
                nodo.getPai().setEsquerda(nodo.getDireita());
            }
            nodo.setPai(nodo.getDireita());
            nodo.setDireita(nodo.getDireita().getEsquerda());
            nodo.getPai().setEsquerda(nodo);
        }
    }

    @Override
    public void inserir(int valor) {
        if (super.getRaiz() == null) {
            super.setRaiz(new Nodo(valor));
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            inserir(novoNodo, super.getRaiz());
            calcularFatorDeBalanceamento(super.getRaiz());
        }
    }

    @Override
    public void inserir(Nodo novoNodo, Nodo pai) {
        if (novoNodo.getValor() < pai.getValor()) {
            if (pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                novoNodo.setPai(pai);
                return;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if (pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                novoNodo.setPai(pai);
                return;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }

    @Override
    public void removerUltimo() {
        if (super.getRaiz() == null) {
            return;
        }

        if (super.getRaiz().getDireita() == null) {
            Nodo novaRaiz = super.getRaiz().getEsquerda();
            novaRaiz.setPai(null);
            super.setRaiz(novaRaiz);
            return;
        }

        Nodo pai = super.getRaiz();
        removerUltimo(pai, pai.getDireita());
    }

    @Override
    public void removerUltimo(Nodo pai, Nodo atual) {
        if (atual.getDireita() == null) {
            Nodo novoUltimo = atual.getEsquerda();
            if(novoUltimo != null){
                novoUltimo.setPai(pai);
            }
            pai.setDireita(novoUltimo);
            return;
        }

        removerUltimo(atual, atual.getDireita());
    }

    @Override
    public void removerInicio() {
        if (super.getRaiz() == null) {
            return;
        }

        if (super.getRaiz().getEsquerda() == null) {
            Nodo novaRaiz =  super.getRaiz().getDireita();
            novaRaiz.setPai(null);
            super.setRaiz(novaRaiz);
            return;
        }

        Nodo pai = super.getRaiz();
        removerInicio(pai, pai.getEsquerda());
    }

    @Override
    public void removerInicio(Nodo pai, Nodo atual) {
        if (atual.getEsquerda() == null) {
            Nodo novoInicio = atual.getDireita();
            if(novoInicio != null){
                novoInicio.setPai(pai);
            }
            pai.setEsquerda(novoInicio);
            return;
        }

        removerInicio(atual, atual.getEsquerda());
    }
}
