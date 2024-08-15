import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL {
    private Nodo raiz;

    public ArvoreAVL(int raiz) {
        this.raiz = new Nodo(raiz);
    }

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void inserir(int valor) {
        if (this.raiz == null) {
            this.raiz = new Nodo(valor);
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            inserir(novoNodo, this.raiz);
            calcularFatorDeBalanceamento(this.raiz);
        }
    }

    public void inserir(Nodo novoNodo, Nodo pai) {
        if (novoNodo.getValor() < pai.getValor()) {
            if (pai.getEsquerda() == null) {
                pai.setEsquerda(novoNodo);
                novoNodo.setPai(pai);
                return;
            }
            pai = pai.getEsquerda();
            inserir(novoNodo, pai);
        } else {
            if (pai.getDireita() == null) {
                pai.setDireita(novoNodo);
                novoNodo.setPai(pai);
                return;
            }
            pai = pai.getDireita();
            inserir(novoNodo, pai);
        }
    }
    
    public int calcularFatorDeBalanceamento(Nodo nodo) {
        if (nodo != null) {
            if(nodo.getEsquerda() == null && nodo.getDireita() == null){
                nodo.setAltura(0);
            }
            int fatorDeBalanceamentoEsquerda = calcularFatorDeBalanceamento(nodo.getEsquerda());
            int fatorDeBalanceamentoDireita = calcularFatorDeBalanceamento(nodo.getDireita());
            int alturaEsquerda = nodo.getEsquerda().getAltura();
            int alturaDireita = nodo.getDireita().getAltura();
            int fatorDeBalanceamento = alturaEsquerda - alturaDireita;

            System.out.println();
            ArvoreAVL aavl = new ArvoreAVL(0);
            aavl.setRaiz(nodo);
            App.printTree(aavl);
            System.out.println();

            if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == 1){
                    // Rotação simples à direita
                    if(this.raiz == nodo){

                    } else {
                        Nodo e = nodo.getEsquerda().getDireita();
                        nodo.getPai().setEsquerda(nodo.getEsquerda());
                        
                    }
            } else if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == -1){
                    // Rotação dupla à esquerda/direita
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoEsquerda == -1) {
                    // Rotação simples à esquerda
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoEsquerda == 1) {
                    // Rotação dupla à direita/esquerda
            }

            if(fatorDeBalanceamento == 2 || fatorDeBalanceamento == -2)
            System.out.println(fatorDeBalanceamento + ": " + nodo.getValor());

            nodo.setAltura(Math.max(alturaEsquerda, alturaDireita) + 1);
            return fatorDeBalanceamento;
        } else {
            return -1;
        }
    }

    private void setRaiz(Nodo nodo) {
        this.raiz = nodo;
    }

    public void removerUltimo() {
        if (this.raiz == null) {
            return;
        }

        if (this.raiz.getDireita() == null) {
            Nodo novaRaiz = this.raiz.getEsquerda();
            novaRaiz.setPai(null);
            this.raiz = novaRaiz;
            return;
        }

        Nodo pai = this.raiz;
        removerUltimo(pai, pai.getDireita());
    }

    public void removerUltimo(Nodo pai, Nodo atual) {
        if (atual.getDireita() == null) {
            Nodo novoUltimo = atual.getEsquerda();
            if (novoUltimo != null) {
                novoUltimo.setPai(pai);
            }
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
            novaRaiz.setPai(null);
            this.raiz = novaRaiz;
            return;
        }

        Nodo pai = this.raiz;
        removerInicio(pai, pai.getEsquerda());
    }

    public void removerInicio(Nodo pai, Nodo atual) {
        if (atual.getEsquerda() == null) {
            Nodo novoInicio = atual.getDireita();
            if (novoInicio != null) {
                novoInicio.setPai(pai);
            }
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
}
