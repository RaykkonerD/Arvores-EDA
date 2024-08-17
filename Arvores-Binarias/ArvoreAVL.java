import java.lang.Math;

public class ArvoreAVL extends ArvoreBinaria {
    public ArvoreAVL(int raiz) {
        super(raiz);
    }

    public Nodo getRaiz() {
        return super.getRaiz();
    }

    public void inserir(int valor) {
        if (super.getRaiz() == null) {
            super.setRaiz(new Nodo(valor));
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            inserir(novoNodo, super.getRaiz());

            System.out.println("::: Inserção :::");
            App.printTree(this);
            System.out.println();

            calcularFatorDeBalanceamento(super.getRaiz());

                System.out.println("::: Balanceamento :::");
                App.printTree(this);
                System.out.println();
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
            int fatorDeBalanceamentoEsquerda = calcularFatorDeBalanceamento(nodo.getEsquerda());
            int fatorDeBalanceamentoDireita = calcularFatorDeBalanceamento(nodo.getDireita());
            int alturaEsquerda = (nodo.getEsquerda() != null) ? nodo.getEsquerda().getAltura() : -1;
            int alturaDireita = (nodo.getDireita() != null) ? nodo.getDireita().getAltura() : -1;
            int fatorDeBalanceamento = alturaEsquerda - alturaDireita;
            

            if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == 1) {
                // Rotação simples à direita
                if (super.getRaiz() == nodo) {
                    super.setRaiz(nodo.getEsquerda());
                    super.getRaiz().setPai(null);
                    Nodo aDireita = super.getRaiz().getDireita();

                    if (aDireita != null) {
                        aDireita.setPai(nodo);
                    }

                    nodo.setEsquerda(aDireita);
                    nodo.setPai(super.getRaiz());
                    super.getRaiz().setDireita(nodo);
                } else {
                    nodo.getEsquerda().setPai(nodo.getPai());
                    nodo.getPai().setEsquerda(nodo.getEsquerda());
                    nodo.setEsquerda(nodo.getEsquerda().getDireita());

                    if (nodo.getEsquerda() != null) {
                        nodo.getEsquerda().setPai(nodo);
                    }

                    nodo.setPai(nodo.getPai().getEsquerda());
                    nodo.getPai().setDireita(nodo);
                }
            } else if (fatorDeBalanceamento == 2 && fatorDeBalanceamentoEsquerda == -1) {
                // Rotação dupla à esquerda/direita
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoDireita == -1) {
                // Rotação simples à esquerda
            } else if (fatorDeBalanceamento == -2 && fatorDeBalanceamentoDireita == 1) {
                // Rotação dupla à direita/esquerda
            }

            nodo.setAltura(Math.max(alturaEsquerda, alturaDireita) + 1);
            return fatorDeBalanceamento;
        } else {
            return -1;
        }
    }

    public void removerUltimo() {
        if (super.getRaiz() == null) {
            return;
        }

        if (super.getRaiz().getDireita() == null) {
            Nodo novaRaiz = super.getRaiz().getEsquerda();
            novaRaiz.setPai(null);
            super.setRaiz(novaRaiz);
            return;
        }

        Nodo pai = super.getRaiz();
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
        if (super.getRaiz() == null) {
            return;
        }

        if (super.getRaiz().getEsquerda() == null) {
            Nodo novaRaiz = super.getRaiz().getDireita();
            novaRaiz.setPai(null);
            super.setRaiz(novaRaiz);
            return;
        }

        Nodo pai = super.getRaiz();
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
        return buscar(super.getRaiz(), valor);
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
        percorrerPreOrdem(super.getRaiz());
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
        percorrerEmOrdem(super.getRaiz());
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
        percorrerPosOrdem(super.getRaiz());
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
