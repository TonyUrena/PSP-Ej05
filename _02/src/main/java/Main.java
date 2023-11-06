import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class Main {
//
//    El enunciado original del problema de los filósofos reza tal que así:
//
//    Cinco filósofos se sientan alrededor de una mesa y pasan su vida cenando y pensando.
//    Cada filósofo tiene un plato de fideos y un tenedor a la izquierda de su plato.
//    Para comer los fideos son necesarios dos tenedores y cada filósofo sólo puede tomar los que están a su izquierda y derecha.
//    Si cualquier filósofo toma un tenedor y el otro está ocupado, se quedará esperando, con el tenedor en la mano,
//    hasta que pueda tomar el otro tenedor, para luego empezar a comer.
//
//    He seguido éste enunciado para mi implementación, ya que el enunciado propuesto en el ejercicio está incompleto
//    y algunos puntos clave para resolverlo no están claros.


    public static void main(String[] args) {

        final int CANTIDAD_FILOSOFOS = 5;

        Mesa mesa = new Mesa(CANTIDAD_FILOSOFOS);

        Set<Integer> posicionesAleatorias = buscaNombresAleatorios(CANTIDAD_FILOSOFOS);
        generaFilosofos(posicionesAleatorias, mesa);

    }

    private static void generaFilosofos(Set<Integer> posicionesAleatorias, Mesa mesa) {
        // Usamos stream para recorrer el set de posiciones. Para cada posición instanciamos un hilo-filósofo con el nombre
        // aleatorio elegido y lo arrancamos.
        System.out.println("-----------------------------------------------");
        posicionesAleatorias.stream().forEach(pos -> {
            Filosofo filo = new Filosofo(
                    Filosofo.getNombresFilosofos()[pos],
                    mesa,
                    posicionesAleatorias.stream().toList().indexOf(pos)); // tomar así el índice del objeto me parece algo guarro, pero no se me ocurre otra manera.
            filo.start();
            System.out.println(filo.getNombre() + " se sienta a la mesa.");
        });

        System.out.println("-----------------------------------------------");
    }

    // He generado una lista de ~100 filósofos históricos y eligo aleatoriamente una cantidad de ellos sin que
    // se repita con el siguiente código. Para aplicar un poco el resto de conocimientos del curso.
    private static Set<Integer> buscaNombresAleatorios(int CANTIDAD_FILOSOFOS) {

        // Creamos un set para almacenar posiciones aleatorias dentro de la lista de nombres de filósofos.
        // Un set no puede almacenar entradas repetidas, por lo que es ideal para el propósito.
        Set<Integer> posicionesAleatorias = new HashSet<>();
        Random random = new Random();

        // Mientras el set no tenga la cantidad de filosofos necesaria, seguimos eligiendo posiciones aleatorias.
        // Un set no permite entradas repetidas, por lo que en este caso se sobreescriben y el tamaño del set no cambia
        // hasta que hayan N valores sin repetirse.
        while(posicionesAleatorias.size() < CANTIDAD_FILOSOFOS){
            posicionesAleatorias.add(random.nextInt(Filosofo.getNombresFilosofos().length));
        }
        return posicionesAleatorias;
    }
}
