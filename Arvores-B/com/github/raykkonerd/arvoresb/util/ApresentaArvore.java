package com.github.raykkonerd.arvoresb.util;

import com.github.raykkonerd.arvoresb.model.ArvoreB;
import com.github.raykkonerd.arvoresb.model.Chave;
import com.github.raykkonerd.arvoresb.model.Nodo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Math;

// public class ApresentaArvore {

//     public static void execute(ArvoreB arvore) {
//         if (arvore == null || arvore.getRaiz() == null) {
//             System.out.println("A árvore está vazia.");
//             return;
//         }
//         imprimirNodo(arvore, arvore.getRaiz(), "", true);
//     }

//     private static void imprimirNodo(ArvoreB arvore, Nodo nodo, String indentacao, boolean ultimo) {
//         if (nodo != null) {
//             System.out.print(indentacao);
//             if (ultimo) {
//                 System.out.print("└── ");
//                 indentacao += "    ";
//             } else {
//                 System.out.print("├── ");
//                 indentacao += "│   ";
//             }
    
    
//             List<Integer> valores = nodo.getChaves().stream()
//             .map(Chave::getValor)
//             .collect(Collectors.toList());
            
//             System.out.println(valores);
            
//             List<Chave> chaves = new ArrayList<>(nodo.getChaves());
//             for (int i = 0; i < chaves.size(); i++) {
//                 if (chaves.get(i) != null && chaves.get(i).getFilhoAEsquerda() != null) {
//                     imprimirNodo(arvore, chaves.get(i).getFilhoAEsquerda(), indentacao, false);
//                 }
//             }
//             if (chaves.size() > 0 && chaves.get(chaves.size() - 1) != null && chaves.get(chaves.size() - 1).getFilhoADireita() != null) {
//                 imprimirNodo(arvore, chaves.get(chaves.size() - 1).getFilhoADireita(), indentacao, true);
//             }
//         }
//     }    
// }

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApresentaArvore {

    public static void execute(ArvoreB arvore) {
        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        imprimirNodo(arvore, arvore.getRaiz(), "", true, new HashSet<>());
    }

    private static void imprimirNodo(ArvoreB arvore, Nodo nodo, String indentacao, boolean ultimo, Set<Nodo> visitados) {
        if (nodo != null) {
            if (visitados.contains(nodo)) {
                System.out.println("Detectado ciclo, interrompendo a impressão.");
                System.out.println("Nó de loop: " + nodo.getChaves().get(0).getValor());
                return;
            }
            visitados.add(nodo);

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
                //.map(chave -> "e: " + (chave.getFilhoAEsquerda() != null ? chave.getFilhoAEsquerda().getChaves().get(0).getValor() : "null") + " <" + chave.getValor() + "> d: " + (chave.getFilhoADireita() != null ? chave.getFilhoADireita().getChaves().get(0).getValor() : "null"))

            System.out.println(valores);
            //System.out.println(" (" + (nodo.getPai() != null ? nodo.getPai().getChaves().get(0).getValor() : "null") + ")");

            List<Chave> chaves = new ArrayList<>(nodo.getChaves());
            for (int i = 0; i < chaves.size(); i++) {
                if (chaves.get(i) != null && chaves.get(i).getFilhoAEsquerda() != null) {
                    imprimirNodo(arvore, chaves.get(i).getFilhoAEsquerda(), indentacao, false, visitados);
                }
            }
            if (chaves.size() > 0 && chaves.get(chaves.size() - 1) != null && chaves.get(chaves.size() - 1).getFilhoADireita() != null) {
                imprimirNodo(arvore, chaves.get(chaves.size() - 1).getFilhoADireita(), indentacao, true, visitados);
            }
        }
    }
}