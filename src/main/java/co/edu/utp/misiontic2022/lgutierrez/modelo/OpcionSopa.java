package co.edu.utp.misiontic2022.lgutierrez.modelo;

public class OpcionSopa {
    
    private Integer id;
    private String nombre;

    public OpcionSopa(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getNombre();
    }   
}
