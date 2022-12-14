package co.edu.utp.misiontic2022.lgutierrez.modelo;
import static co.edu.utp.misiontic2022.lgutierrez.modelo.EstadoPedido.*;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private Integer id;
    private String cliente;
    private EstadoPedido estado;
    private Corrientazo almuerzo;
    private List<Adicional> adicionales;  
    
    public Pedido(String cliente, Corrientazo almuerzo) {
        this.cliente = cliente;
        this.almuerzo = almuerzo;

        estado = SIN_ENTREGAR;
        adicionales = new ArrayList<>();
    }

    public void agregarAdicional (Adicional adicional){
        this.adicionales.add(adicional);
    }

    public void entregarPedido(){
        estado = PENDIENTE_COBRAR;
    }

    public Integer calcularValor(){
        return almuerzo.getPrecio() + adicionales.stream()
                .map(adicional -> adicional.getPrecio())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }
   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlmuerzo(Corrientazo almuerzo) {
        this.almuerzo = almuerzo;
    }

    public String getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }
    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Corrientazo getAlmuerzo() {
        return almuerzo;
    }

    public void setAdicionales(List<Adicional> adicionales) {
        this.adicionales = adicionales;
    }

    @Override
    public String toString() {
        return "Pedido [ cliente=" + cliente + ", almuerzo=" + almuerzo + ", adicionales=" + adicionales +  ", estado="
                + estado + "]";
    }  

    
    
}
