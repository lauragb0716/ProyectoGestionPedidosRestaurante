package co.edu.utp.misiontic2022.lgutierrez;

import co.edu.utp.misiontic2022.lgutierrez.modelo.*;

/**
 * Hello world!
 */
public final class App {
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        var mesa = new Mesa("01");

        var almuerzo1 = new Corrientazo(12_000, 
                                        new OpcionSopa("Patacón"),
                                        new OpcionPrincipio("Frijoles"), 
                                        new OpcionCarne("Cerdo a la plancha"),
                                        new OpcionEnsalada("Cesar"),
                                        new OpcionJugo("Limonada"));
        var pedidoAnderson = new Pedido("Anderson", almuerzo1);

        var almuerzo2 = new Corrientazo(12_000, 
                                        new OpcionSopa("Raiz"),
                                        new OpcionPrincipio("Espagietis"), 
                                        new OpcionCarne("Pollo sudado"),
                                        new OpcionJugo("Maracuyá"));
        var pedidoCristian = new Pedido("Cristian", almuerzo2);

        mesa.agregarPedido(pedidoAnderson);
        mesa.agregarPedido(pedidoCristian);

        pedidoAnderson.agregarAdicional(new Adicional("Chicharron", 3_000));
        pedidoAnderson.agregarAdicional(new Adicional("Huevo", 800));

        System.out.printf("Total de la mesa: $ %,d %n", mesa.calcularValor());
        
        var efectivo = 30_000;
        try {
            System.out.printf("Se paga con $ %,d y le devuelve $ %,d %n", efectivo, mesa.pagar(efectivo));
        } catch (PagoExcepcion e) {
            System.err.printf("Se paga con $ %,d y %s. %n", efectivo, e.getMessage());
        }

        System.out.println(mesa.calcularValor());
        
    }
}
