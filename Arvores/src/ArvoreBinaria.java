import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArvoreBinaria {
    private Nodo raiz;

    public ArvoreBinaria(int raiz) {
        this.raiz = new Nodo(raiz);
    }

    public void inserir(int valor) {
        Nodo novoNodo = new Nodo(valor);
        inserir(novoNodo, this.raiz);
    }

    public void inserir(Nodo novoNodo, Nodo pai) {
        if (novoNodo.getValor() < pai.getValor()) {
            if (pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                return;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if (pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                return;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }

    public void removerUltimo() {
        
    }

    public void removerInicio() {

    }

    @Override
    public String toString() {
        List<List<Nodo>> niveis = NodosPorNivel();

        // int quantNulls = 0;
        // for (int i = 0; i < niveis.size(); i++) {
        //     for (Nodo nodo : niveis.get(i)) {
        //         if(nodo == null){
        //             quantNulls++;
        //         }
        //     }
        // }

        String str = "";
        for (int i = 0; i < niveis.size(); i++) {
            str += "Level " + i + ": ";
            for (int j = 0; j < (niveis.size() - i) * 2; j++) {
                str += " ";
            }

            for (Nodo nodo : niveis.get(i)) {
                str += ((nodo == null) ? "*" : nodo.getValor());
                for (int k = 0; k < (niveis.size() - i) * 2; k++) {
                    str += " ";
                }
            }
            // for (Nodo nodo : niveis.get(i)) {
            // System.out.print((nodo == null) ? "null" : nodo.getValor());
            // }
            // System.out.println();
            str += "\n";
        }
        return str;
    }

    public List<List<Nodo>> NodosPorNivel() {
        List<List<Nodo>> niveis = new ArrayList<>();
        if (this.raiz == null) {
            return niveis;
        }

        Queue<Nodo> nivel = new LinkedList<>();
        nivel.add(this.raiz);

        while (!nivel.isEmpty()) {
            int nivelTamanho = nivel.size();
            List<Nodo> currentLevel = new ArrayList<>();

            for (int i = 0; i < nivelTamanho; i++) {
                Nodo nodo = nivel.poll();
                currentLevel.add(nodo);
                if (nodo == null) {
                    continue;
                } else {
                    if (nodo.getEsquerda() != null) {
                        nivel.add(nodo.getEsquerda());
                    } else {
                        nivel.add(null);
                    }

                    if (nodo.getDireita() != null) {
                        nivel.add(nodo.getDireita());
                    } else {
                        nivel.add(null);
                    }
                }
            }

            niveis.add(currentLevel);
        }

        return niveis;
    }
}
