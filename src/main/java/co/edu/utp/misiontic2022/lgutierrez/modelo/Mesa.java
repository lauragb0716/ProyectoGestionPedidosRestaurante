package co.edu.utp.misiontic2022.lgutierrez.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private String numero;
    private List<Pedido> pedidos;

    public Mesa(String numero) {
        this.numero = numero;
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    public Integer calcularValor(){
        var total = pedidos.stream()
        .map(pedido -> pedido.calcularValor())
        .reduce((a, b) -> a + b)
        .orElse(0);

        return total;
    }

    public Integer pagar(Integer efectivo) throws PagoExcepcion{
        //Valido si es suficiente para pagar
        //var total = calcularValorMesa();
        //if (efectivo < total) {
        //    throw new PagoExcepcion("El efectivo no es suficiente para cubrir la cuenta");
        //} else {
            
        //}

        //Elimino los pedidos de la mesa

        //Retorna la devuelta
        //var devuelta = efectivo - calcularValor();
        return 0;
    }

    public String getNumero() {
        return numero;
    }

    

    

}
