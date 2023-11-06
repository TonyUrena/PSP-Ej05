import java.util.Random;

class Consumidor extends Thread {

    int threadID;
    private ListaElementos listaElementos;

    Consumidor(int id, ListaElementos listaElementos) {
        this.threadID = id;
        this.listaElementos = listaElementos;
    }

    @Override
    public void run() {
        super.run();
        listaElementos.consume(threadID);
    }

}