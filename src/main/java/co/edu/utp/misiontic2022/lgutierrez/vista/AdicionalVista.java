package co.edu.utp.misiontic2022.lgutierrez.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.controlador.RestauranteControlador;
import co.edu.utp.misiontic2022.lgutierrez.modelo.Adicional;

public class AdicionalVista {

    private Scanner sc;
    private RestauranteControlador controlador;

    public AdicionalVista(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public Adicional elegirAdicional() throws SQLException {
        System.out.println("\n.: ELIJA EL ADICIONAL QUE DESEA :.\n");

        var opciones = controlador.getAdicional();
       
        do {
            try {
                System.out.println("Las opciones son: ");

                for (int i = 0; i < opciones.size(); i++) {
                    System.out.printf("%d -> %s %n", i + 1, opciones.get(i));
                }

                System.out.print("\nCuál es su opción?: ");
                var opcion = sc.nextInt();

                if (opcion >= 1 && opcion <= opciones.size()) {
                    return opciones.get(opcion - 1);
                } else {
                    System.err.println("Opción inválida, intente de nuevo \n");

                }
            } catch (InputMismatchException e) {
                System.err.println("Opción inválida, intente de nuevo \n");
            } finally {
                // Un truco para cuando se utiliza un next diferente al line, ya que me queda el
                // enter en memoria
                sc.nextLine();
            }
        } while (true);
    }
    
}
