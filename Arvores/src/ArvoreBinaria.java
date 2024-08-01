import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.w3c.dom.Node;

public class ArvoreBinaria {
    private Nodo raiz;

    public ArvoreBinaria(int raiz){
        this.raiz = new Nodo(raiz);
    }

    public void inserir(int valor){
        Nodo novoNodo = new Nodo(valor);
        Nodo pai = this.raiz;
        
        if(valor < pai.getValor()) {
            if(pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                return ;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if(pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                return ;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }

    public void inserir(Nodo novoNodo, Nodo pai){
        if(novoNodo.getValor() < pai.getValor()) {
            if(pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                return ;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if(pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                return ;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }

    @Override
    public String toString(){
        List<List<Nodo>> niveis = NodosPorNivel();
        String str = "";
        for (int i = 0; i < niveis.size(); i++) {
            str += "Level " + i + ": ";
            for(int j = 0; j < niveis.size() - i; j++){
                str += " ";
            }
            for (Nodo nodo : niveis.get(i)) {
                str += ((nodo == null) ? "null" : nodo.getValor()) + " ";
            }
            str += "\n";
        }
        return str;
    }

    public List<List<Nodo>> NodosPorNivel() {
        List<List<Nodo>> niveis = new ArrayList<>();
        if (this.raiz == null) {
            return niveis;
        }

        Queue<Nodo> queue = new LinkedList<>();
        queue.add(this.raiz);

        while (!queue.isEmpty()) {
            int niveisize = queue.size();
            List<Nodo> currentLevel = new ArrayList<>();

            for (int i = 0; i < niveisize; i++) {
                Nodo nodo = queue.poll();
                currentLevel.add(nodo);

                if (nodo.getEsquerda() != null) {
                    queue.add(nodo.getEsquerda());
                } else {
                    queue.add(null);
                }

                if (nodo.getDireita() != null) {
                    queue.add(nodo.getDireita());
                } else {
                    queue.add(null);
                }
            }

            niveis.add(currentLevel);
        }

        return niveis;
    }
}