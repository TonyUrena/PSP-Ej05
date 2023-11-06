public class Main {
    public static void main(String[] args) {

        final int CANTIDAD_PS5 = 20;
        final int CANTIDAD_CLIENTES = 200;
        final int CANTIDAD_INTENTOS = 3;

        Tienda tienda = new Tienda(CANTIDAD_PS5);

        for (int i = 0; i < CANTIDAD_CLIENTES; i++) {
            Cliente cliente = new Cliente(Integer.toString(i),CANTIDAD_INTENTOS, tienda);
            tienda.getListaClientes().add(cliente);
        }
        tienda.getListaClientes().stream().forEach(Cliente::start);
    }
}
