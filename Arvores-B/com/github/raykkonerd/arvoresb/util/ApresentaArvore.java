package com.github.raykkonerd.arvoresb.util;

import com.github.raykkonerd.arvoresb.model.ArvoreB;
import com.github.raykkonerd.arvoresb.model.Chave;
import com.github.raykkonerd.arvoresb.model.Nodo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApresentaArvore {

    public static void execute(ArvoreB arvore) {
        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        imprimirNodo(arvore, arvore.getRaiz(), "", true);
    }

    private static void imprimirNodo(ArvoreB arvore, Nodo nodo, String indentacao, boolean ultimo) {
        if (nodo != null) {
            System.out.print(indentacao);
            if (ultimo) {
                System.out.print("└── ");
                indentacao += "    ";
            } else {
                System.out.print("├── ");
                indentacao += "│   ";
            }
    
    
            List<Integer> valores = nodo.getChaves().stream()
            .map(Chave::getValor)
            .collect(Collectors.toList());
            
            System.out.println(valores);
            
            List<Chave> chaves = new ArrayList<>(nodo.getChaves());
            for (int i = 0; i < chaves.size(); i++) {
                if (chaves.get(i) != null && chaves.get(i).getFilhoAEsquerda() != null) {
                    imprimirNodo(arvore, chaves.get(i).getFilhoAEsquerda(), indentacao, false);
                }
            }
            if (chaves.size() > 0 && chaves.get(chaves.size() - 1) != null && chaves.get(chaves.size() - 1).getFilhoADireita() != null) {
                imprimirNodo(arvore, chaves.get(chaves.size() - 1).getFilhoADireita(), indentacao, true);
            }
        }
    }    
}