import java.util.ArrayList;
import java.util.List;

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
        apresentarArvore(arvore);
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

        // Inserção
        arvore.inserir(9);
        arvore.inserir(7);
        arvore.inserir(6);
        arvore.inserir(8);
        arvore.inserir(5);
        arvore.inserir(4);
        arvore.inserir(3);
        arvore.inserir(2);
        
        // Apresentação
        // System.out.println();
        // apresentarArvore(arvore);
        // System.out.println();
    }

    public static void apresentarArvore(ArvoreBinaria arvore) {
        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        List<List<String>> linhas = new ArrayList<>();
        List<Nodo> nivel = new ArrayList<>();
        List<Nodo> proximoNivel = new ArrayList<>();
        nivel.add(arvore.getRaiz());
        int nNodosNoNivel = 1;
        int valorMaisLongo = 0;

        while (nNodosNoNivel != 0) {
            List<String> linha = new ArrayList<>();
            nNodosNoNivel = 0;

            for (Nodo nodo : nivel) {
                if (nodo == null) {
                    linha.add(null);
                    proximoNivel.add(null);
                    proximoNivel.add(null);
                } else {
                    String valor = String.valueOf(nodo.getValor() + "(" + ((nodo.getPai() != null) ? nodo.getPai().getValor() : "null") + ")");
                    linha.add(valor);
                    if (valor.length() > valorMaisLongo) valorMaisLongo = valor.length();

                    proximoNivel.add(nodo.getEsquerda());
                    proximoNivel.add(nodo.getDireita());

                    if (nodo.getEsquerda() != null) nNodosNoNivel++;
                    if (nodo.getDireita() != null) nNodosNoNivel++;
                }
            }

            if (valorMaisLongo % 2 == 1) valorMaisLongo++;

            linhas.add(linha);

            List<Nodo> tmp = nivel;
            nivel = proximoNivel;
            proximoNivel = tmp;
            proximoNivel.clear();
        }

        int larguraDoNivel = linhas.get(linhas.size() - 1).size() * (valorMaisLongo + 4);
        for (int i = 0; i < linhas.size(); i++) {
            List<String> linha = linhas.get(i);
            int metadeDaLarguraDoNivel = (int) Math.floor(larguraDoNivel / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < linha.size(); j++) {
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (linha.get(j - 1) != null) {
                            c = (linha.get(j) != null) ? '|' : ' ';
                        } else {
                            if (j < linha.size() && linha.get(j) != null) c = ' ';
                        }
                    }
                    System.out.print(c);

                    if (linha.get(j) == null) {
                        for (int k = 0; k < larguraDoNivel - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < metadeDaLarguraDoNivel; k++) {
                            System.out.print(j % 2 == 0 ? " " : "-");
                        }
                        System.out.print(j % 2 == 0 ? '|' : '|');
                        for (int k = 0; k < metadeDaLarguraDoNivel; k++) {
                            System.out.print(j % 2 == 0 ? "-" : " ");
                        }
                    }
                }
                System.out.println();
            }

            for (int j = 0; j < linha.size(); j++) {
                String f = linha.get(j);
                if (f == null) f = "";
                int espacamento1 = (int) Math.ceil(larguraDoNivel / 2f - f.length() / 2f);
                int espacamento2 = (int) Math.floor(larguraDoNivel / 2f - f.length() / 2f);

                for (int k = 0; k < espacamento1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < espacamento2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            larguraDoNivel /= 2;
        }
    }
}
