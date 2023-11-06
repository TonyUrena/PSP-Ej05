import java.util.Random;

public class Cliente extends Thread {

    private final Tienda tienda;
    private int intentosCliente;
    private final String nombreCliente;
    private boolean
            haComprado = false,
            seHaMarchado = false;

    @Override
    public void run() {
        super.run();
        while(!seHaMarchado){
            try {
                intentaComprar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    Cliente(String nombreCliente, int intentosCliente, Tienda tienda) {
        this.nombreCliente = nombreCliente;
        this.intentosCliente = intentosCliente;
        this.tienda = tienda;
    }

    private synchronized void intentaComprar() throws InterruptedException {
        Random random = new Random();

        if (random.nextBoolean()) {
            if (tienda.getCantidadPlaystation5() > 0) {
                tienda.compraPs5();
                System.out.println(nombreCliente + " ha conseguido una PS5 y se marcha muy contento.");
                seHaMarchado = true;
            } else {
                System.out.println(nombreCliente + " no ha conseguido una PS5 y se marcha mosqueado.");
                seHaMarchado = true;
            }
        } else {
            intentosCliente--;
            System.out.println(nombreCliente + " no encuentra la entrada.");
            if (intentosCliente < 1) {
                System.out.println(nombreCliente + " se ha hartado y se ha marchado.");
                seHaMarchado = true;
            }
            Thread.sleep(random.nextInt(0, 5001));
        }

    }

    public Tienda getTienda() {
        return tienda;
    }

    public int getIntentosCliente() {
        return intentosCliente;
    }

    public void setIntentosCliente(int intentosCliente) {
        this.intentosCliente = intentosCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public boolean isHaComprado() {
        return haComprado;
    }

    public void setHaComprado(boolean haComprado) {
        this.haComprado = haComprado;
    }

    public boolean isSeHaMarchado() {
        return seHaMarchado;
    }

    public void setSeHaMarchado(boolean seHaMarchado) {
        this.seHaMarchado = seHaMarchado;
    }
}
