public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria ab = new ArvoreBinaria(10);
        ab.inserir(5);
        ab.inserir(8);
        ab.inserir(20);
        System.out.println(ab);
    }
}
