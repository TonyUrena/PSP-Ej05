import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mesa {
    private Filosofo filosofos[];

    // Representamos con un array cada cubierto a la izquierda de cada filósofo
    // Utilizamos AtomicBoolean para evitar condiciones de carrera al acceder a los cubiertos
    // Estos dos arrays estan alineados, es decir, al filósofo de la posición 0 le corresponde
    // el cubierto 0 como cubierto de su izquierda.
    private AtomicBoolean cubiertos[];

    Mesa(int cantidadFilosofos){
        this.cubiertos = new AtomicBoolean[cantidadFilosofos];
        Arrays.fill(cubiertos,new AtomicBoolean(true));
        this.filosofos = new Filosofo[cantidadFilosofos];
    }

    public void cogeCubierto(int posicionCubierto){
        cubiertos[posicionCubierto].set(false);
    }

    public void dejaCubierto(int posicionCubierto){
        cubiertos[posicionCubierto].set(true);
    }



    ////////////////////////////////////////////////////////////
    public Filosofo[] getFilosofos() {
        return filosofos;
    }

    public void setFilosofos(Filosofo[] filosofos) {
        this.filosofos = filosofos;
    }

    public AtomicBoolean[] getCubiertos() {
        return cubiertos;
    }

    public void setCubiertos(AtomicBoolean[] cubiertos) {
        this.cubiertos = cubiertos;
    }
}
