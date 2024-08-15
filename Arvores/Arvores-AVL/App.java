import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreAVL arvore = new ArvoreAVL(10);

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

        // Desbalanceamento
        arvore.inserir(3);
        
        // Apresentação
        System.out.println();
        printTree(arvore);
        System.out.println();

        arvore.calcularFatorDeBalanceamento(arvore.getRaiz());

        // Percurso
        arvore.percorrerPreOrdem();
        arvore.percorrerEmOrdem();
        arvore.percorrerPosOrdem();

        // Buscar
        System.out.println("22 " + (arvore.buscar(22) ? "está" : "não está") + " na árvore!");
        System.out.println("5 " + (arvore.buscar(5) ? "está" : "não está") + " na árvore!");
        System.out.println("10 " + (arvore.buscar(10) ? "está" : "não está") + " na árvore!");
        System.out.println("3 " + (arvore.buscar(3) ? "está" : "não está") + " na árvore!");
        System.out.println("30 " + (arvore.buscar(30) ? "está" : "não está") + " na árvore!");
    }

    public static void printTree(ArvoreAVL arvore) {
        if (arvore == null || arvore.getRaiz() == null) {
            System.out.println("A árvore está vazia.");
            return;
        }
        List<List<String>> linhas = new ArrayList<>();
        List<Nodo> nivel = new ArrayList<>();
        List<Nodo> proximo = new ArrayList<>();
        nivel.add(arvore.getRaiz());
        int nn = 1;
        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;

            for (Nodo n : nivel) {
                if (n == null) {
                    line.add(null);
                    proximo.add(null);
                    proximo.add(null);
                } else {
                    String aa = String.valueOf(n.getValor());
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    proximo.add(n.getEsquerda());
                    proximo.add(n.getDireita());

                    if (n.getEsquerda() != null) nn++;
                    if (n.getDireita() != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            linhas.add(line);

            List<Nodo> tmp = nivel;
            nivel = proximo;
            proximo = tmp;
            proximo.clear();
        }

        int perpiece = linhas.get(linhas.size() - 1).size() * (widest + 4);
        for (int i = 0; i < linhas.size(); i++) {
            List<String> line = linhas.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {
                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '|' : ' ';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = ' ';
                        }
                    }
                    System.out.print(c);

                    // linhas and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "-");
                        }
                        System.out.print(j % 2 == 0 ? '|' : '|');
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "-" : " ");
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
