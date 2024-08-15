import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

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

    public static void printTree(BinaryTree tree) {
        if (tree == null || tree.getRoot() == null) {
            System.out.println("The tree is empty.");
            return;
        }
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();
        level.add(tree.getRoot());
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = String.valueOf(n.getValue());
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getLeft());
                    next.add(n.getRight());

                    if (n.getLeft() != null) nn++;
                    if (n.getRight() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {
                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
}
