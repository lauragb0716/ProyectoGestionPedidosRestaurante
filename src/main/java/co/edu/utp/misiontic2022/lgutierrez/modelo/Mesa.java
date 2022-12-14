package co.edu.utp.misiontic2022.lgutierrez.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private Integer id;
    private String numero;
    private List<Pedido> pedidos;

    public Mesa(String numero) {
        this.numero = numero;
        this.pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNumero() {
        return numero;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        return "Mesa # " + numero;
    }

    public void borrarPedidos() {
        pedidos.clear();
    }

    

}
