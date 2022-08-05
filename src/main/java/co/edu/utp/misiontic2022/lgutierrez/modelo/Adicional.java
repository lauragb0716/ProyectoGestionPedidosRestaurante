package co.edu.utp.misiontic2022.lgutierrez.modelo;

public class Adicional {
    private Integer id;
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

    public Integer getId() {
        return id;
    }  
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    

}
