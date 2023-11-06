import java.util.ArrayList;
import java.util.Random;

public class Tienda {

    Random random = new Random();
    int cantidadPlaystation5;
    ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    Tienda(int cantidadPlaystation5) {
        this.cantidadPlaystation5 = cantidadPlaystation5;
    }

    public synchronized void compraPs5() throws InterruptedException {
        if (cantidadPlaystation5 > 0) {
            cantidadPlaystation5--;
        }
    }

    public int getCantidadPlaystation5() {
        return cantidadPlaystation5;
    }

    public void setCantidadPlaystation5(int cantidadPlaystation5) {
        this.cantidadPlaystation5 = cantidadPlaystation5;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}
