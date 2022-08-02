package co.edu.utp.misiontic2022.lgutierrez;

import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.controlador.RestauranteContolador;

/**
 * Hello world!
 */
public final class App {
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        try (var sc = new Scanner(System.in)) {
            var controlador = new RestauranteContolador(sc);
            controlador.cargarBaseDatos();
            controlador.iniciarAplicacion();
            

        } catch (Exception e) {
            System.err.println("Ocurrió un error en la aplicación!!");
            System.err.println("\t" + e.getMessage());
        }   
    }
}
