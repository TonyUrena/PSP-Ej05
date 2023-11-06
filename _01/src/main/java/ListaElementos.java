import java.util.Random;

public class ListaElementos {

    public int
            punteroProductor = 0,
            punteroConsumidor = 0,
            tamanyoLista = 4,
            contenidoLista = 0;

    public int[] elementos = new int[tamanyoLista];

    public boolean isFull() {
        return contenidoLista == tamanyoLista;
    }

    public boolean isEmpty() {
        return contenidoLista <= 0;
    }

    public synchronized int produce(int threadID, int producto) {
        Random random = new Random();
        try {
            // Si la lista está llena, ponemos el productor en espera
            if (isFull()) {
                System.out.println("Lista Llena, productor " + threadID + " en espera");
                wait();
            } else {
                Thread.sleep(random.nextInt(1000, 2000 + 1));
                producto++;

                // Colocamos el producto en la posición donde apunta el puntero del productor
                elementos[punteroProductor] = producto;
                System.out.println("Productor " + threadID + " produjo " + producto + " en " + punteroProductor);

                // Actualizamos la cantidad de productos en la lista
                contenidoLista++;
                System.out.println("Contenido lista: " + contenidoLista);

                // La siguiente línea es prácticamente mágica. Avanza la posición y calcula su resto contra el tamaño de la lista.
                // si el puntero tiene el mismo valor que el tamaño de la lista, el resultado es 0. Es una forma super eficiente de
                // devolver el puntero a la posición 0.
                punteroProductor = (punteroProductor + 1) % tamanyoLista;

                // Notificamos a los hilos en espera de que ya hay contenido disponible en la lista
                notifyAll();
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    public synchronized void consume(int threadID) {
        Random random = new Random();
        try {
            while (true) {
                if (isEmpty()) {
                    System.out.println("Lista Vacía, consumidor " + threadID + " en espera");
                    wait();
                } else {
                    Thread.sleep(random.nextInt(1000, 2000 + 1));

                    // Consumimos el producto en la posición del puntero Consumidor
                    System.out.println("Consumidor " + threadID + " consume: " + elementos[punteroConsumidor] + " en: " + punteroConsumidor);
                    elementos[punteroConsumidor] = 0;

                    // La siguiente línea es prácticamente mágica. Avanza la posición y calcula el resto contra el tamaño de la lista.
                    // si el puntero tiene el mismo valor que el tamaño de la lista, el resultado es 0. Es una forma super eficiente de
                    // devolver el puntero a la posición 0.
                    punteroConsumidor = (punteroConsumidor + 1) % tamanyoLista;

                    // Actualizamos la lista de productos
                    contenidoLista--;
                    System.out.println("Contenido Lista: " + contenidoLista);

                    // Avisamos a los hilos en espera de que ya hay espacio en la lista
                    notifyAll();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


