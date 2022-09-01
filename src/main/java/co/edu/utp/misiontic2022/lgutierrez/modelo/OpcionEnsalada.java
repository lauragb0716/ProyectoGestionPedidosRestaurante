package co.edu.utp.misiontic2022.lgutierrez.modelo;

import java.util.Objects;

public class OpcionEnsalada {

    private Integer id;
    private String nombre;

    public OpcionEnsalada(String nombre) {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final OpcionEnsalada other = (OpcionEnsalada) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
