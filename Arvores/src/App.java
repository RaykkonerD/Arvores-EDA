public class App {
    public static void main(String[] args) throws Exception {
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

        // Apresentação
        System.out.println();
        System.out.println(arvore);

        // Remoção
        arvore.removerUltimo();
        arvore.removerInicio();
        arvore.removerInicio();
        
        // Apresentação
        System.out.println();
        System.out.println(arvore);
        System.out.println();

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
}
