import java.lang.Math;

public class ArvoreAVL extends ArvoreBinaria {
    public ArvoreAVL(int raiz) {
        super(raiz);
    }

    @Override
    public void inserir(int valor) {
        if (super.getRaiz() == null) {
            super.setRaiz(new Nodo(valor));
        } else if (!buscar(valor)) {
            Nodo novoNodo = new Nodo(valor);
            super.inserir(novoNodo, super.getRaiz());

            System.out.println("::: Inserção :::");
            App.apresentarArvore(this);
            System.out.println();

            calcularFatorDeBalanceamento(super.getRaiz());

                System.out.println("::: Balanceamento :::");
                App.apresentarArvore(this);
                System.out.println();
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
                rotacaoADireita(nodo);
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

    public void rotacaoADireita(Nodo nodo){
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
    }
}
