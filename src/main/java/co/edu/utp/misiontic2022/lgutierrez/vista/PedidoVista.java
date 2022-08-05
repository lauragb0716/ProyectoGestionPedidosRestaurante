package co.edu.utp.misiontic2022.lgutierrez.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.controlador.RestauranteControlador;
import co.edu.utp.misiontic2022.lgutierrez.modelo.*;

public class PedidoVista {

    private static final int PRECIO_CORRIENTAZO = 12_000;
    private Scanner sc;
    private RestauranteControlador controlador;

    public PedidoVista(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public Pedido pedirInformacionPedido() throws SQLException {
        System.out.println("\n.: INGRESANDO EL PEDIDO :.\n");

        System.out.print("Ingrese el nombre (descripción) del cliente: ");
        var cliente = sc.nextLine();

        var sopa = elegirOpcionSopa();
        var principio = elegirOpcionPrincipio();
        var carne = elegirOpcionCarne();
        var ensalada = elegirOpcionEnsalada();
        var jugo = elegirOpcionJugo();

        return new Pedido(cliente, new Corrientazo(PRECIO_CORRIENTAZO, sopa, principio, carne, ensalada, jugo));
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private OpcionSopa elegirOpcionSopa() throws SQLException {
        System.out.println("\n.: ELIJA LA OPCION DE SOPA :.\n");
        var opciones = controlador.getSopas();

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

    private OpcionPrincipio elegirOpcionPrincipio () throws SQLException {
        System.out.println("\n.: ELIJA LA OPCION DE PRINCIPIO :.\n");
        var opciones = controlador.getPrincipios();

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

    private OpcionCarne elegirOpcionCarne() throws SQLException {
        System.out.println("\n.: ELIJA LA OPCION DE CARNE :.\n");
        var opciones = controlador.getCarnes();

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

    private OpcionEnsalada elegirOpcionEnsalada() throws SQLException {
        System.out.println("\n.: ELIJA LA OPCION DE ENSALADA :.\n");
        var opciones = controlador.getEnsaladas();

        do {
            try {
                System.out.println("Las opciones son: ");
                System.out.printf("%d -> %s %n", 0, "Sin ensalada");

                for (int i = 0; i < opciones.size(); i++) {
                    System.out.printf("%d -> %s %n", i + 1, opciones.get(i));
                }

                System.out.print("\nCuál es su opción?: ");
                var opcion = sc.nextInt();
                if (opcion == 0) {
                    return null;
                }else if (opcion >= 1 && opcion <= opciones.size()) {
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

    private OpcionJugo elegirOpcionJugo() throws SQLException {
        System.out.println("\n.: ELIJA LA OPCION DE JUGO :.\n");
        var opciones = controlador.getJugos();

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
