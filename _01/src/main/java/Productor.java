import java.util.Random;

class Productor extends Thread {

    int threadID, producto;
    private ListaElementos listaElementos;

    Productor(int id, ListaElementos elementos) {
        this.threadID = id;
        this.listaElementos = elementos;
    }

    @Override
    public void run() {
        super.run();
        while (this.producto < 30) {
            this.producto = listaElementos.produce(threadID, producto);
        }
    }
}
