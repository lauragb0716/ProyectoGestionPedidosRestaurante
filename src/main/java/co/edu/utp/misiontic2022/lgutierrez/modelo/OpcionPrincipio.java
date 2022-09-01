package co.edu.utp.misiontic2022.lgutierrez.modelo;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpcionPrincipio other = (OpcionPrincipio) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
