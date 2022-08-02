package co.edu.utp.misiontic2022.lgutierrez.controlador;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import co.edu.utp.misiontic2022.lgutierrez.exception.PagoException;
import co.edu.utp.misiontic2022.lgutierrez.modelo.*;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.CarneDao;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.EnsaladaDao;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.JugoDao;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.MesaDao;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.PrincipioDao;
import co.edu.utp.misiontic2022.lgutierrez.modelo.dao.SopaDao;
import co.edu.utp.misiontic2022.lgutierrez.vista.*;

public class RestauranteContolador {

    private MenuPrincipal menuPrincipal;
    private MesaVista mesaVista;
    private PedidoVista pedidoVista;

    private MesaDao mesaDao;
    private SopaDao sopaDao;
    private PrincipioDao principioDao;
    private CarneDao carneDao;
    private EnsaladaDao ensaladaDao;
    private JugoDao jugoDao;

    public RestauranteContolador(Scanner sc) {
        this.menuPrincipal = new MenuPrincipal(sc, this);
        this.mesaVista = new MesaVista(sc, this);
        this.pedidoVista = new PedidoVista(sc, this);
        
        this.mesaDao = new MesaDao();
        this.sopaDao = new SopaDao();
        this.principioDao = new PrincipioDao();
        this.carneDao = new CarneDao();
        this.ensaladaDao = new EnsaladaDao();
        this.jugoDao = new JugoDao();
    }

    // TODO: Solo para las pruebas
    public void cargarBaseDatos() throws SQLException {
        /*mesaDao.guardar(new Mesa("01"));
        mesaDao.guardar(new Mesa("02"));
        mesaDao.guardar(new Mesa("03"));
        mesaDao.guardar(new Mesa("04"));
        mesaDao.guardar(new Mesa("05"));
        mesaDao.guardar(new Mesa("06"));
        mesaDao.guardar(new Mesa("07"));*/

        /*sopaDao.guardar(new OpcionSopa("Pasta"));
        sopaDao.guardar(new OpcionSopa("Sancocho"));
        sopaDao.guardar(new OpcionSopa("Crema ahuyama"));
        sopaDao.guardar(new OpcionSopa("Patacón"));
        sopaDao.guardar(new OpcionSopa("Verduras"));
        sopaDao.guardar(new OpcionSopa("Ajiaco"));*/

        /*principioDao.guardar(new OpcionPrincipio("Frijoles"));
        principioDao.guardar(new OpcionPrincipio("Lentejas"));
        principioDao.guardar(new OpcionPrincipio("Papa guisada"));
        principioDao.guardar(new OpcionPrincipio("Espaguettis"));*/

        /*carneDao.guardar(new OpcionCarne("Res a la plancha"));
        carneDao.guardar(new OpcionCarne("Cerdo a la plancha"));
        carneDao.guardar(new OpcionCarne("Pechuga a la plancha"));
        carneDao.guardar(new OpcionCarne("Chicharrón"));
        carneDao.guardar(new OpcionCarne("Carne molida"));
        carneDao.guardar(new OpcionCarne("En Bistec"));*/

        /*ensaladaDao.guardar(new OpcionEnsalada("Solo tomate"));
        ensaladaDao.guardar(new OpcionEnsalada("Tomate y cebolla"));
        ensaladaDao.guardar(new OpcionEnsalada("Dulce"));
        ensaladaDao.guardar(new OpcionEnsalada("Remolacha y zanahoria"));*/

        /*jugoDao.guardar(new OpcionJugo("Limonada"));
        jugoDao.guardar(new OpcionJugo("Guayaba"));
        jugoDao.guardar(new OpcionJugo("Mora"));
        jugoDao.guardar(new OpcionJugo("Lulo"));
        jugoDao.guardar(new OpcionJugo("Maracuyá"));*/
    }

    public List<Mesa> getMesas() throws SQLException {
        return mesaDao.listar();
    }

    public List<OpcionSopa> getSopas() throws SQLException {
        return sopaDao.listar();
    }

    public List<OpcionPrincipio> getPrincipios() throws SQLException {
        return principioDao.listar();
    }

    public List<OpcionCarne> getCarnes() throws SQLException {
        return carneDao.listar();
    }

    public List<OpcionEnsalada> getEnsaladas() throws SQLException {
        return ensaladaDao.listar();
    }

    public List<OpcionJugo> getJugos() throws SQLException {
        return jugoDao.listar();
    }

    public void crearMesa() throws SQLException {
        // Pedir al usuario la informacón necesaria para crear la mesa
        Mesa mesa = mesaVista.pedirInformacionMesa();

        // Almacenar la mesa
        this.mesaDao.guardar(mesa);;

        // Listar las mesas que se encuentran en el sistema
        mesaVista.mostrarMesas(getMesas());

    }

    public void agregarPedido(Mesa mesa) throws SQLException {
        // Pedir al usuario la información del pedido
        var pedido = pedidoVista.pedirInformacionPedido();

        // Agregar el pedido a la mesa
        mesa.agregarPedido(pedido);

        // Mostrar confirmación de agregar el pedido
        pedidoVista.mostrarMensaje("Se ha recibido el pedido de " + pedido.getCliente());
    }

    public Mesa consultarMesa() throws SQLException {
        return mesaVista.consultarMesa();
    }

    public void entregarPedido(Mesa mesa) {
        // Seleccionar pedido de mesa
        Pedido pedido = mesaVista.seleccionarPedido(mesa);

        // Marcar como entregado el pedido
        pedido.entregarPedido();
    }

    public void mostrarPedidos(Mesa mesa) {
        mesaVista.mostrarPedidos(mesa);
    }

    public void agregarAdicional() {
    }

    public void pagarCuenta(Mesa mesa) {
        var efectivo = mesaVista.leerValorEfectivo();

        try {
            // Valido si es suficiente para pagar
            var total = mesa.calcularValor();
            if (efectivo < total) {
                throw new PagoException("El efectivo no es suficiente para cubrir la cuenta");
            }

            // Elimino los pedidos de la mesa
            mesa.borrarPedidos();

            // Retorna la devuelta
            mesaVista.mostrarMensaje(String.format("La devuelta son: $ %,d", (efectivo-total)));
        } catch (PagoException e) {
            mesaVista.mostrarMensaje(e.getMessage());
        }        
    }

    public void iniciarAplicacion() throws SQLException {
        menuPrincipal.iniciarAplicacion();
    }
}
