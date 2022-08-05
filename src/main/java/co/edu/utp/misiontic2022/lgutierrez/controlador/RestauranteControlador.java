package co.edu.utp.misiontic2022.lgutierrez.controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import co.edu.utp.misiontic2022.lgutierrez.exception.PagoException;
import co.edu.utp.misiontic2022.lgutierrez.modelo.*;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.*;
import co.edu.utp.misiontic2022.lgutierrez.vista.*;

public class RestauranteControlador {

    private MenuPrincipal menuPrincipal;
    private MesaVista mesaVista;
    private PedidoVista pedidoVista;
    private AdicionalVista adicionalVista;

    private MesaDao mesaDao;
    private PedidoDao pedidoDao;
    private AdicionalDao adicionalDao;

    //Se utiliza la plantilla de OpcionAlimentoDao para crear los objetos que se requieren
    private OpcionAlimentoDao<OpcionSopa> opcionSopaDao;
    private OpcionAlimentoDao<OpcionPrincipio> opcionPrincipioDao;
    private OpcionAlimentoDao<OpcionCarne> opcionCarneDao;
    private OpcionAlimentoDao<OpcionEnsalada> opcionEnsaladaDao;
    private OpcionAlimentoDao<OpcionJugo> opcionJugoDao;

    public RestauranteControlador(Scanner sc) {
        this.menuPrincipal = new MenuPrincipal(sc, this);
        this.mesaVista = new MesaVista(sc, this);
        this.pedidoVista = new PedidoVista(sc, this);
        this.adicionalVista = new AdicionalVista(sc, this);
        
        //Siempre los objetos se deben inicializar en el contructor
        this.mesaDao = new MesaDao();
        this.pedidoDao = new PedidoDao();
        this.adicionalDao = new AdicionalDao();

        this.opcionSopaDao = new OpcionAlimentoDao<>("OpcionSopa");
        this.opcionPrincipioDao = new OpcionAlimentoDao<>("OpcionPrincipio");
        this.opcionCarneDao = new OpcionAlimentoDao<>("OpcionCarne");
        this.opcionEnsaladaDao = new OpcionAlimentoDao<>("OpcionEnsalada");
        this.opcionJugoDao = new OpcionAlimentoDao<>("OpcionJugo");
    }

    public List<Mesa> getMesas() throws SQLException {
        return mesaDao.listar();
    }

    public List<Pedido> getPedidos(Mesa mesa) throws SQLException {
        return pedidoDao.listar(mesa);
    }

    public List<Adicional> getAdicional() throws SQLException {
        return adicionalDao.listar();
    }

    public List<OpcionSopa> getSopas() throws SQLException {
        //Defino la función que recibe como parametro el método listar.
        //Se define una lambda que recibe un ResultSet(rset).
        return opcionSopaDao.listar(rset -> {
            //Como las lambdas no pueden propagar excepciones, se debe colocar un bloque try-catch
            try{
                //Se define lo que se desea hacer con la función del método, creando un objeto del tipo de la opción que se requiere y se le agregan los atributos
                var opcion = new OpcionSopa(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
                return opcion;
            } catch(SQLException e){
                return null;
            }
        });
    }

    public List<OpcionPrincipio> getPrincipios() throws SQLException {
        return opcionPrincipioDao.listar(rset -> {
            try{
                var opcion = new OpcionPrincipio(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
                return opcion;
            } catch(SQLException e){
                return null;
            }
        });
    }

    public List<OpcionCarne> getCarnes() throws SQLException {
        return opcionCarneDao.listar(rset -> {
            try{
                var opcion = new OpcionCarne(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
                return opcion;
            } catch(SQLException e){
                return null;
            }
        });
    }

    public List<OpcionEnsalada> getEnsaladas() throws SQLException {
        return opcionEnsaladaDao.listar(rset -> {
            try{
                var opcion = new OpcionEnsalada(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
                return opcion;
            } catch(SQLException e){
                return null;
            }
        });
    }

    public List<OpcionJugo> getJugos() throws SQLException {
        return opcionJugoDao.listar(rset -> {
            try{
                var opcion = new OpcionJugo(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));
                return opcion;
            } catch(SQLException e){
                return null;
            }
        });
    }

    public void crearMesa() throws SQLException {
        // Pedir al usuario la informacón necesaria para crear la mesa
        Mesa mesa = mesaVista.pedirInformacionMesa();

        // Almacenar la mesa
        this.mesaDao.guardar(mesa);;

        // Listar las mesas que se encuentran en el sistema
        mesaVista.mostrarMesas(getMesas());

    }

    public void agregarPedido(Mesa mesa) {
        try{
            // Pedir al usuario la información del pedido
        var pedido = pedidoVista.pedirInformacionPedido();

        // Agregar el pedido a la mesa
        pedidoDao.guardar(mesa, pedido);

        // Mostrar confirmación de agregar el pedido
        pedidoVista.mostrarMensaje("Se ha recibido el pedido de " + pedido.getCliente());
        } catch(SQLException e){
            System.err.println("Error accediendo a la base de datos: " + e.getMessage());
        }
    }

    public Mesa consultarMesa() throws SQLException {
        return mesaVista.consultarMesa();
    }

    public void entregarPedido(Mesa mesa){
        try {
            // Seleccionar pedido de mesa
            //Se filtran los pedidos que están pendientes sin entregar y los convierte en una lista
            var pedidos = pedidoDao.listar(mesa).stream()
                .filter(p -> p.getEstado() == EstadoPedido.SIN_ENTREGAR)
                .collect(Collectors.toList());
            Pedido pedido = mesaVista.seleccionarPedido(pedidos);

            // Marcar como entregado el pedido internamente en el sistema
            pedido.entregarPedido();
            // Se envía el pedido cambiado como parámetro al metodo que actualiza la base de datos.
            pedidoDao.entregarPedido(pedido);
            pedidoVista.mostrarMensaje(String.format("El pedido de %s fue entregado", pedido.getCliente()));
        } catch (Exception e) {
            System.err.println("Error entregando pedidos: " + e.getMessage());
        }
        
    }

    public void mostrarPedidos(Mesa mesa) {
        try{
            var pedidos = pedidoDao.listar(mesa);
            mesaVista.mostrarPedidos(pedidos);
        } catch(SQLException e){
            System.err.println("Error obteniendo pedidos: " + e.getMessage());
        }
    }

    public void agregarAdicional(Mesa mesa) throws SQLException {
        try{
            // Pedir al usuario que escoja el pedido que le desea adicionar
            var pedidos = pedidoDao.listar(mesa);
            Pedido pedido = mesaVista.seleccionarPedido(pedidos);

            
            //Pedir al usuario que escoja el adicional que quiere
            var adicional = adicionalVista.elegirAdicional();
            
            // Agregar el pedido a la mesa
            adicionalDao.guardar(pedido, adicional);

            // Mostrar confirmación de agregar el pedido
            pedidoVista.mostrarMensaje(String.format("Se ha adicionado '%s', al pedido de %s", adicional, pedido.getCliente()));
        } catch(SQLException e){
            System.err.println("Error accediendo a la base de datos: " + e.getMessage());
        }
    }

    public void pagarCuenta(Mesa mesa) {
        try{
            //Traigo los pedidos de la base de datos
            var pedidos = pedidoDao.listar(mesa);
            //Realizo una lambda para calcular el total de cada pedido
            var total = pedidos.stream()
            .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
            .map(pedido -> pedido.calcularValor())
            .reduce((a, b) -> a + b)
            .orElse(0);
            //Muestro el valor del pedido
            pedidoVista.mostrarMensaje(String.format("La cuenta es: $ %,d", total));
            //Solicito el efectivo
            var efectivo = mesaVista.leerValorEfectivo();

            try {
                // Valido si es suficiente para pagar
                if (efectivo < total) {
                    throw new PagoException("El efectivo no es suficiente para cubrir la cuenta");
                }
                // Elimino los pedidos de la mesa en la base de datos
                pedidoDao.eliminarPedidosDeMesa(mesa);              

                // Retorna la devuelta
                mesaVista.mostrarMensaje(String.format("La devuelta son: $ %,d", (efectivo-total)));
            } catch (PagoException e) {
                mesaVista.mostrarMensaje(e.getMessage());
            }        
        } catch(SQLException e){
            System.err.println("Error al pagar la cuenta: " + e.getMessage());
        }
    }
    public void iniciarAplicacion() throws SQLException {
        menuPrincipal.iniciarAplicacion();
    }

}
