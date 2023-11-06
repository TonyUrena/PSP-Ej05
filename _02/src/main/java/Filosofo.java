import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Filosofo extends Thread {
    final int NUTRICION_MAXIMA = 100;
    Random random = new Random();

    String nombre;
    int hambre = NUTRICION_MAXIMA;
    boolean palilloDerecho = false,
            palilloIzquierdo = false;
    int posicionEnMesa;
    int posicionPalilloIzquierdo, posicionPalilloDerecho
            ;
    Mesa mesa;

    Filosofo(String nombre, Mesa mesa, int posicionEnMesa) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.posicionEnMesa = posicionEnMesa;
        this.posicionPalilloIzquierdo = posicionEnMesa;
        this.posicionPalilloDerecho = (posicionPalilloIzquierdo + 1) % mesa.getCubiertos().length;
    }

    @Override
    public void run() {
        super.run();
        while (sigueVivo()) {
            try {
                buscaCubiertos();
                if (palilloDerecho && palilloIzquierdo) come();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Función recursiva, si el hambre del filósofo baja de la mitad de su
    // nutrición, busca un cubierto a su izquierda o derecha.
    public void buscaCubiertos() throws InterruptedException {

        Thread.sleep(1000);

        // Este bloque ignoto de spaguetti seguramente puede condensarse con métodos para elegir los palillos.

        if (!this.palilloIzquierdo && !this.palilloDerecho) {
            System.out.println(this.nombre + " no tiene ningún palillo, y busca un cubierto aleatorio");
            int posiciónPalillo = random.nextInt(0,2);

            // Elije el palillo a su izquierda
            if (posiciónPalillo == 0 && mesa.getCubiertos()[posicionEnMesa].get()) {
                mesa.cogeCubierto(posicionPalilloIzquierdo);
                this.palilloIzquierdo = true;
                System.out.println(this.nombre + " coje el palillo a su izquierda");

            // Elije el palillo a su derecha
            } else if (posiciónPalillo == 1 && mesa.getCubiertos()[posicionPalilloDerecho].get()){
                mesa.cogeCubierto(posicionPalilloDerecho);
                this.palilloDerecho = true;
                System.out.println(this.nombre + " coje el palillo a su derecha");
            }

        } else if (!this.palilloDerecho && mesa.getCubiertos()[posicionPalilloDerecho].get()) {
            // Elije el palillo a su derecha
            mesa.cogeCubierto(posicionPalilloDerecho);
            this.palilloDerecho = true;
            System.out.println(this.nombre + " coje el palillo a su derecha");

        } else if (!this.palilloIzquierdo && mesa.getCubiertos()[posicionPalilloIzquierdo].get()) {
            // Elije el palillo a su izquierda
            mesa.cogeCubierto(posicionPalilloIzquierdo);
            this.palilloIzquierdo = true;
            System.out.println(this.nombre + " coje el palillo a su izquierda");

        } else {
            come();
        }
        pasaHambre();
    }

    public void come() throws InterruptedException {
        hambre = NUTRICION_MAXIMA;
        this.palilloDerecho = false;
        this.palilloIzquierdo = false;
        System.out.println(this.nombre + " ha comido.");
        mesa.dejaCubierto(posicionPalilloIzquierdo);
        mesa.dejaCubierto(posicionPalilloDerecho);
        piensa();
    }

    public void piensa() throws InterruptedException {
        Thread.sleep(random.nextInt(1000, 5001));
        System.out.println(this.nombre + "está pensando...");
        pasaHambre();
    }


    private void pasaHambre() throws InterruptedException {
        if (hambre > 0) hambre--;
        if (hambre < NUTRICION_MAXIMA / 2) buscaCubiertos();
    }

    // Si el hambre baja a 0, el filosofo muere;
    private boolean sigueVivo() {
        if (hambre < 1) System.out.println(nombre + " ha muerto de hambre.");
        return hambre > 0;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static String[] getNombresFilosofos() {
        return nombresFilosofos;
    }

    static String[] nombresFilosofos = {"Adorno", "Al-Farabi", "Aristipo de Cirene", "Aristóteles",
            "Avicena", "Averroes", "Averroes", "Bacon", "Bentham", "Bergson",
            "Boecio", "Camus", "Cicerón", "Clemente de Alejandría", "Confucio",
            "Crates de Tebas", "Crisipo de Solos", "Diderot", "Diógenes de Apolonia",
            "Diógenes de Sinope", "Duns Escoto", "Empédocles", "Epicteto", "Epicuro",
            "Epicuro", "Esquines de Megara",  "Espinosa", "Fichte", "Foucault",
            "Gorgias de Leontini", "Guillermo de Ockham",
            "Hegel", "Heráclito", "Hume", "Kant", "Karl Marx", "Kierkegaard", "Laozi",
            "Leibniz", "Locke", "Maimónides", "Maquiavelo", "Meliso de Samos", "Mengzi",
            "Mill", "Montesquieu", "Nietzsche", "Orígenes", "Parménides de Elea",
            "Pascal", "Pitágoras", "Platón", "Plotino", "Popper", "Protágoras", "Rawls",
            "Ricoeur", "Rousseau", "Russell", "San Agustín", "San Anselmo", "Santo Tomás de Aquino",
            "Sartre", "Savater", "Schopenhauer", "Sexto Empírico", "Sócrates", "Spinoza",
            "Tales de Mileto", "Wittgenstein", "Xenófanes", "Zenón de Elea",
            "Zeno de Citio"};
}