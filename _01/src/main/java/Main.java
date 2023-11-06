public class Main {

    public static void main(String[] args) {

        ListaElementos elementos = new ListaElementos();

        for (int i = 0; i < 3; i++) {
            Productor p = new Productor(i, elementos);
            p.start();
        }
        for (int i = 0; i < 2; i++) {
            Consumidor c = new Consumidor(i, elementos);
            c.start();
        }
    }
}
