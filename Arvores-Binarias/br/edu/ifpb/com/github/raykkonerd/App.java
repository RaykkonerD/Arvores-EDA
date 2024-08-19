package com.github.raykkonerd;

import com.github.raykkonerd.model.ArvoreAVL;
import com.github.raykkonerd.model.ArvoreBinaria;
import com.github.raykkonerd.util.ApresentaArvore;

public class App {
    public static void testesArvoreBinaria(){
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

    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL(10);

        // Rotação Simples à Direita
            arvore.inserir(5);
            arvore.inserir(3);

            // Apresentação
            ApresentaArvore.execute(arvore);
        
            // Limpando filhos
            arvore.removerInicio();
            arvore.removerInicio();

        // Rotação Simples à Esquerda
            arvore.inserir(15);
            arvore.inserir(20);

            // Apresentação
            ApresentaArvore.execute(arvore);
            
            // Limpando filhos
            arvore.removerUltimo();
            arvore.removerUltimo();

        // Rotação Dupla Esquerda/Direita
            arvore.inserir(15);
            arvore.inserir(12);

            // Apresentação
            ApresentaArvore.execute(arvore);
            
            // Limpando filhos
            arvore.removerUltimo();
            arvore.removerUltimo();

        // Rotação Dupla Direita/Esquerda
            arvore.inserir(5);
            arvore.inserir(7);

            // Apresentação
            ApresentaArvore.execute(arvore);
            
            // Limpando filhos
            arvore.removerUltimo();
            arvore.removerUltimo();
    }
}
