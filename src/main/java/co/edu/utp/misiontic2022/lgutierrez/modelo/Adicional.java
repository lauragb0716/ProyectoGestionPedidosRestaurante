package co.edu.utp.misiontic2022.lgutierrez.modelo;

public class Adicional {
    private String nombre;
    private Integer precio;
    
    public Adicional(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getPrecio() {
        return precio;
    }   

}
