package co.edu.utp.misiontic2022.lgutierrez.vista;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.controlador.RestauranteContolador;

public class MenuPrincipal {

    private Scanner sc;
    private RestauranteContolador controlador;

    public MenuPrincipal(Scanner sc, RestauranteContolador contolador) {
        this.sc = sc;
        this.controlador = contolador;
    }

    public void iniciarAplicacion() throws SQLException {
        var enMenu = true;
        do {
            try {
                System.out.println("\n.: MENU PRINCIPAL :.\n");
                System.out.println("1 -> Gestión de Pedidos");
                System.out.println("2 -> Pagos de mesas");
                System.out.println("3 -> Tablas maestras");
                System.out.println("0 -> Salir");
                System.out.print("\nCuál es su opción?: ");

                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        System.out.println("\n.:HASTA PRONTO:.\n");
                        enMenu = false;
                        break;
                    case 1:
                        abrirMenuGestionPedidos();
                        break;
                    case 2:
                        pagosDeMesa();
                        break;
                    case 3:
                        abrirMenuTablasMaestras();
                        break;
                    
                    default:
                        System.err.println("Opcion inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Opcion inválida, intente de nuevo");
                sc.nextLine();
            }
        } while (enMenu);
    }

    private void abrirMenuGestionPedidos() throws SQLException {
        var mesa = controlador.consultarMesa();
        
        var salida = false;
        while (!salida) {
            try {
                System.out.println("\n.: GESTIÓN DE PEDIDOS :.\n");
                System.out.println("1 -> Ver pedidos de mesa");
                System.out.println("2 -> Agregar Pedidos");
                System.out.println("3 -> Agregar Adicional a pedido");
                System.out.println("4 -> Entregar pedido");
                System.out.println("0 -> Salir al menú principal");
                System.out.print("\nCuál es su opción?: ");

                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        salida = true;
                        break;
                    case 1:
                        controlador.mostrarPedidos(mesa);
                        salida = true;
                        break;
                    case 2:
                        controlador.agregarPedido(mesa);
                        salida = true;
                        break;
                    case 3:
                        //TODO Implementar método 
                        controlador.agregarAdicional();
                        salida = true;
                        break;
                    case 4:
                        controlador.entregarPedido(mesa);
                        salida = true;
                        break;
                    default:
                        System.err.println("Opcion inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Opcion inválida, intente de nuevo");
                sc.nextLine();
            }
        }
    }

    private void pagosDeMesa() throws SQLException {
        var mesa = controlador.consultarMesa();
        controlador.pagarCuenta(mesa);
    }

    private void abrirMenuTablasMaestras() {

        var salida = false;
        while (!salida) {
            try {
                System.out.println("\n.: TABLAS MAESTRAS :.\n");
                System.out.println("1 -> Mesa");
                System.out.println("2 -> Opciones de Sopa");
                System.out.println("3 -> Opciones de Principios");
                System.out.println("4 -> Opciones de Carne");
                System.out.println("5 -> Opciones de Ensalada");
                System.out.println("6 -> Opciones de Jugo");
                System.out.println("7 -> Opciones de Adicionales");
                System.out.println("0 -> Salir al menú principal");
                System.out.print("\nCuál es su opción?: ");

                var opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 0:
                        salida = true;
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:

                        break;
                    default:
                        System.err.println("Opcion inválida, intente de nuevo");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Opcion inválida, intente de nuevo");
                sc.nextLine();
            }
        }
    }
}
