package co.edu.utp.misiontic2022.lgutierrez.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.controlador.RestauranteControlador;
import co.edu.utp.misiontic2022.lgutierrez.modelo.Mesa;
import co.edu.utp.misiontic2022.lgutierrez.modelo.Pedido;

public class MesaVista {

    private Scanner sc;
    private RestauranteControlador controlador;

    public MesaVista(Scanner sc, RestauranteControlador controlador) {
        this.sc = sc;
        this.controlador = controlador;
    }

    public Mesa pedirInformacionMesa() {

        System.out.println("\n.: INFORMACIÓN DE LA MESA :.\n");
        System.out.print("Ingrese el número de la mesa a registrar: ");
        var numero = sc.nextLine();

        return new Mesa(numero);
    }

    public void mostrarMesas(List<Mesa> mesas) {
        System.out.println("\n.: MESAS EN EL SISTEMA :.\n");
        mesas.forEach(System.out::println);
    }

    public Mesa consultarMesa() throws SQLException {
        System.out.println("\n.: CONSULTANDO MESAS :.\n");
        var opciones = controlador.getMesas();

        do {
            try {
                System.out.println("Las mesas existentes son: ");

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

    public Pedido seleccionarPedido(List<Pedido> opciones) {
        System.out.println("\n.: ELIJA EL PEDIDO :.\n");

        Pedido respuesta = null;

        do {
            try {
                System.out.println("Las opciones son: ");

                for (int i = 0; i < opciones.size(); i++) {
                    System.out.printf("%d -> %s %n", i + 1, opciones.get(i));
                }

                System.out.print("\nCuál es su opción?: ");
                var opcion = sc.nextInt();

                if (opcion >= 1 && opcion <= opciones.size()) {
                    respuesta = opciones.get(opcion - 1);
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

        } while (respuesta == null);

        return respuesta;
    }

    public Integer leerValorEfectivo() {
        Integer respuesta = null;
        while (respuesta == null) {
            try {
                System.out.print("\nIngrese valor de efectivo: ");
                respuesta = sc.nextInt();
            } catch (Exception e) {
                System.err.println("Valor inválido, intente de nuevo \n");
            } finally {
                sc.nextLine();
            }
        }
        return respuesta;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarPedidos(List<Pedido>opciones) {
        System.out.println("Los pedidos son: ");

        for (int i = 0; i < opciones.size(); i++) {
            System.out.printf("%d -> %s %n", i + 1, opciones.get(i));
        }
    }
}
