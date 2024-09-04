package com.github.raykkonerd.arvoresb.util;

import com.github.raykkonerd.arvoresb.model.ArvoreB;
import com.github.raykkonerd.arvoresb.model.Nodo;

import java.util.Arrays;

public class ApresentaArvore {

    public static void execute(ArvoreB arvore) {
        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        imprimirNodo(arvore.getRaiz(), "", true);
    }

    private static void imprimirNodo(Nodo nodo, String indentacao, boolean ultimo) {
        if (nodo != null) {
            System.out.print(indentacao);
            if (ultimo) {
                System.out.print("└── ");
                indentacao += "    ";
            } else {
                System.out.print("├── ");
                indentacao += "│   ";
            }
            System.out.println(Arrays.toString(Arrays.copyOfRange(nodo.getChaves(), 0, nodo.getChaves().length -1)));
            // Imprime os filhos
            Nodo[] filhos = nodo.getFilhos();
            for (int i = 0; i < filhos.length - 1; i++) {
                if (filhos[i] != null) {
                    imprimirNodo(filhos[i], indentacao, i == filhos.length - 2);
                }
            }
        }
    }
}