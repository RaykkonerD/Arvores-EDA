package com.github.raykkonerd.arvoresb;

import com.github.raykkonerd.arvoresb.model.ArvoreB;
import com.github.raykkonerd.arvoresb.util.ApresentaArvore;

public class App {
    public static void main(String[] args) {
        ArvoreB ab = new ArvoreB(3, 20);
        ab.inserir(15);
        ab.inserir(26);
        ab.inserir(14);
        ab.inserir(18);
        ab.inserir(17);
        ab.inserir(21);
        ab.inserir(19);
        ab.inserir(37);
        ab.inserir(30);
        
        ApresentaArvore.execute(ab);
    }
}
