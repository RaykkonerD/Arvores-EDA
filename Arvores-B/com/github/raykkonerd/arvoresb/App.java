package com.github.raykkonerd.arvoresb;

import com.github.raykkonerd.arvoresb.model.ArvoreB;
import com.github.raykkonerd.arvoresb.util.ApresentaArvore;

public class App {
    public static void main(String[] args) {
        ArvoreB ab = new ArvoreB(4, 20);
        ab.inserir(15);
        ab.inserir(26);
        ab.inserir(30);
        ab.inserir(28);
        ab.inserir(7);
        ab.inserir(10);
        ab.inserir(29);
        ab.inserir(50);
        ab.inserir(11);
        ab.inserir(55);
        ab.inserir(43);
        ab.inserir(18);
        ab.inserir(13);
        ab.inserir(12);
        
        ApresentaArvore.execute(ab);
    }
}