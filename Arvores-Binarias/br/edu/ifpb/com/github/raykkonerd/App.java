package com.github.raykkonerd;

import com.github.raykkonerd.model.ArvoreAVL;
import com.github.raykkonerd.model.ArvoreBinaria;
import com.github.raykkonerd.model.Nodo;
import com.github.raykkonerd.util.ApresentaArvore;

public class App {
    public static void testesArvoreBinaria() {
        ArvoreBinaria arvore = new ArvoreBinaria(10);

        // Inserção
        arvore.inserir(3);
        arvore.inserir(7);
        arvore.inserir(8);
        arvore.inserir(20);
        arvore.inserir(23);
        arvore.inserir(22);
        arvore.inserir(11);
        arvore.inserir(21);
        arvore.inserir(6);
        arvore.inserir(5);
        arvore.inserir(4);
        arvore.inserir(20);

        // Remoção
        arvore.removerUltimo();
        arvore.removerInicio();
        arvore.removerInicio();

        // Apresentação
        System.out.println();
        ApresentaArvore.execute(arvore);
        System.out.println();

        // Percurso
        arvore.percorrerPreOrdem();
        arvore.percorrerEmOrdem();
        arvore.percorrerPosOrdem();

        // Busca
        System.out.println("22 " + (arvore.buscar(22) ? "está" : "não está") + " na árvore!");
        System.out.println("5 " + (arvore.buscar(5) ? "está" : "não está") + " na árvore!");
        System.out.println("10 " + (arvore.buscar(10) ? "está" : "não está") + " na árvore!");
        System.out.println("3 " + (arvore.buscar(3) ? "está" : "não está") + " na árvore!");
        System.out.println("30 " + (arvore.buscar(30) ? "está" : "não está") + " na árvore!");
    }

    public static void testesArvoreAVL(int tipo) {
        ArvoreAVL arvore = new ArvoreAVL(10);

        switch (tipo) {
            case 1:
                System.out.println("### Rotação Simples à Direita ###");
                arvore.inserir(5);
                arvore.inserir(3);

                // Apresentação
                System.out.println("  ->  Na raiz");
                System.out.println("  Ordem de inserção: 10, 5, 3.");
                ApresentaArvore.execute(arvore);

                arvore.setRaiz(new Nodo(10));
                arvore.inserir(11);
                arvore.inserir(5);
                arvore.inserir(3);
                arvore.inserir(2);

                // Apresentação
                System.out.println("  ->  Fora da raiz");
                System.out.println("  Ordem de inserção: 10, 11, 5, 3, 2.");
                ApresentaArvore.execute(arvore);

                // Limpando filhos
                arvore.removerInicio();
                arvore.removerInicio();
                arvore.removerInicio();
                arvore.removerUltimo();

                break;

            case 2:
                System.out.println("### Rotação Simples à Esquerda ###");
                arvore.inserir(15);
                arvore.inserir(20);

                // Apresentação
                System.out.println("  ->  Na raiz");
                System.out.println("  Ordem de inserção: 10, 15, 20.");
                ApresentaArvore.execute(arvore);

                arvore.setRaiz(new Nodo(10));
                arvore.inserir(5);
                arvore.inserir(15);
                arvore.inserir(20);
                arvore.inserir(26);

                // Apresentação
                System.out.println("  ->  Fora da raiz");
                System.out.println("  Ordem de inserção: 10, 5, 15, 20, 26.");
                ApresentaArvore.execute(arvore);

                // Limpando filhos
                arvore.removerInicio();
                arvore.removerUltimo();
                arvore.removerUltimo();
                arvore.removerUltimo();

                break;

            case 3:
                System.out.println("### Rotação Dupla Esquerda/Direita ###");
                arvore.inserir(15);
                arvore.inserir(12);

                // Apresentação
                System.out.println("  ->  Na raiz");
                System.out.println("  Ordem de inserção: 10, 15, 12.");
                ApresentaArvore.execute(arvore);

                arvore.setRaiz(new Nodo(10));
                arvore.inserir(3);
                arvore.inserir(12);
                arvore.inserir(32);
                arvore.inserir(21);

                // Apresentação
                System.out.println("  ->  Fora da raiz");
                System.out.println("  Ordem de inserção: 10, 3, 12, 32, 21.");
                ApresentaArvore.execute(arvore);

                // Limpando filhos
                arvore.removerUltimo();
                arvore.removerUltimo();

                break;

            case 4:
                System.out.println("### Rotação Dupla Direita/Esquerda ###");
                arvore.inserir(5);
                arvore.inserir(7);

                // Apresentação
                System.out.println("  ->  Na raiz");
                System.out.println("  Ordem de inserção: 10, 5, 7.");
                ApresentaArvore.execute(arvore);

                arvore.setRaiz(new Nodo(10));
                arvore.inserir(18);
                arvore.inserir(7);
                arvore.inserir(2);
                arvore.inserir(4);

                // Apresentação
                System.out.println("  ->  Fora da raiz");
                System.out.println("  Ordem de inserção: 10, 18, 7, 2, 4.");
                ApresentaArvore.execute(arvore);

                // Limpando filhos
                arvore.removerUltimo();
                arvore.removerUltimo();

                break;
        }
    }

    public static void main(String[] args) {
        // testesArvoreAVL(4);
        ArvoreAVL arvore = new ArvoreAVL(20);

        arvore.inserir(15);
        arvore.inserir(25);
        arvore.inserir(17);
        arvore.inserir(23);
        arvore.inserir(14);
        arvore.inserir(28);
        arvore.inserir(16);
        arvore.inserir(21);
        arvore.remover(25);
        arvore.remover(15);

        ApresentaArvore.execute(arvore);
    }
}
