import java.lang.Math;
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
        if (this.raiz == null) {
            this.raiz = new Nodo(valor);
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            inserir(novoNodo, this.raiz);
        }
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
        if (this.raiz == null) {
            return;
        }

        if (this.raiz.getDireita() == null) {
            Nodo novaRaiz = this.raiz.getEsquerda();
            this.raiz = novaRaiz;
            return;
        }

        Nodo pai = this.raiz;
        removerUltimo(pai, pai.getDireita());
    }

    public void removerUltimo(Nodo pai, Nodo atual) {
        if (atual.getDireita() == null) {
            Nodo novoUltimo = atual.getEsquerda();
            pai.setDireita(novoUltimo);
            return;
        }

        removerUltimo(atual, atual.getDireita());
    }

    public void removerInicio() {
        if (this.raiz == null) {
            return;
        }

        if (this.raiz.getEsquerda() == null) {
            Nodo novaRaiz = this.raiz.getDireita();
            this.raiz = novaRaiz;
            return;
        }

        Nodo pai = this.raiz;
        removerInicio(pai, pai.getEsquerda());
    }

    public void removerInicio(Nodo pai, Nodo atual) {
        if (atual.getEsquerda() == null) {
            Nodo novoInicio = atual.getDireita();
            pai.setEsquerda(novoInicio);
            return;
        }

        removerInicio(atual, atual.getEsquerda());
    }

    public boolean buscar(int valor) {
        return buscar(this.raiz, valor);
    }

    public boolean buscar(Nodo pai, int valor) {
        if (pai == null) {
            return false;
        } else if (valor < pai.getValor()) {
            return buscar(pai.getEsquerda(), valor);
        } else if (valor > pai.getValor()) {
            return buscar(pai.getDireita(), valor);
        }

        return true;
    }

    public void percorrerPreOrdem() {
        System.out.print("Pré-ordem: ");
        percorrerPreOrdem(this.raiz);
        System.out.println("\n");
    }

    public void percorrerPreOrdem(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getValor() + " ");
            percorrerPreOrdem(nodo.getEsquerda());
            percorrerPreOrdem(nodo.getDireita());
        }
    }

    public void percorrerEmOrdem() {
        System.out.print("Em ordem: ");
        percorrerEmOrdem(this.raiz);
        System.out.println("\n");
    }

    public void percorrerEmOrdem(Nodo nodo) {
        if (nodo != null) {
            percorrerEmOrdem(nodo.getEsquerda());
            System.out.print(nodo.getValor() + " ");
            percorrerEmOrdem(nodo.getDireita());
        }
    }

    public void percorrerPosOrdem() {
        System.out.print("Pós-ordem: ");
        percorrerPosOrdem(this.raiz);
        System.out.println("\n");
    }

    public void percorrerPosOrdem(Nodo nodo) {
        if (nodo != null) {
            percorrerPosOrdem(nodo.getEsquerda());
            percorrerPosOrdem(nodo.getDireita());
            System.out.print(nodo.getValor() + " ");
        }
    }

    @Override
    public String toString() {
        List<List<Nodo>> niveis = nodosPorNivel();

        String str = "";
        for (int i = 0; i < niveis.size(); i++) {
            str += "Level " + i + ": ";
            int nEspacos = (int) (niveis.get(niveis.size() - 1).size() * 2 / Math.pow(2, (i + 1)));

            for (int j = 0; j < nEspacos + niveis.size() - 2 - i; j++) {
                str += " ";
            }

            boolean mesmoPai = true;
            for (Nodo nodo : niveis.get(i)) {
                str += ((nodo == null) ? "*" : nodo.getValor());
                for (int k = 0; k < (mesmoPai ? nEspacos * 2 : nEspacos + niveis.size() - 1 - i); k++) {
                    str += " ";
                }
                mesmoPai = !mesmoPai;
            }
            str += "\n";
        }

        return str;
    }

    public List<List<Nodo>> nodosPorNivel() {
        List<List<Nodo>> niveis = new ArrayList<>();
        if (this.raiz == null) {
            return niveis;
        }

        Queue<Nodo> nivel = new LinkedList<>();
        nivel.add(this.raiz);

        while (!nivelEhUltimo(nivel)) {
            int nivelTamanho = nivel.size();
            List<Nodo> currentLevel = new ArrayList<>();

            for (int i = 0; i < nivelTamanho; i++) {
                Nodo nodo = nivel.poll();
                currentLevel.add(nodo);
                if (nodo == null) {
                    nivel.add(null);
                    nivel.add(null);
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

    public boolean nivelEhUltimo(Queue<Nodo> nivel) {
        for (Nodo nodo : nivel) {
            if (nodo != null) {
                return false;
            }
        }
        return true;
    }
}
