public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria ab = new ArvoreBinaria(10);
        ab.inserir(5);
        ab.inserir(8);
        ab.inserir(20);
        ab.inserir(22);
        ab.inserir(11);
        ab.inserir(7);
        ab.inserir(21);
        // ab.removerUltimo();
        ab.removerInicio();
        ab.removerInicio();
        System.out.println(ab);
        System.out.println();
        System.out.println("22 " + (ab.buscar(22) ? "está" : "não está") + " na árvore!");
        System.out.println("5 " + (ab.buscar(5) ? "está" : "não está") + " na árvore!");
        System.out.println("8 " + (ab.buscar(8) ? "está" : "não está") + " na árvore!");
        System.out.println("30 " + (ab.buscar(30) ? "está" : "não está") + " na árvore!");
    }
}
