package co.edu.utp.misiontic2022.lgutierrez.modelo;

import java.util.Objects;

public class OpcionJugo {

    private Integer id;
    private String nombre;

    public OpcionJugo(String nombre) {
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
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final OpcionJugo other = (OpcionJugo) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
