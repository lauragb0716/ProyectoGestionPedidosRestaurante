package co.edu.utp.misiontic2022.lgutierrez.modelo;

public class OpcionPrincipio {

    private Integer id;
    private String nombre;

    public OpcionPrincipio(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }   
}
