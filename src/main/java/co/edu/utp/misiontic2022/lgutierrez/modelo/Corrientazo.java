package co.edu.utp.misiontic2022.lgutierrez.modelo;

public class Corrientazo {
    private Integer precio;
    private OpcionSopa sopa;
    private OpcionPrincipio principio;
    private OpcionCarne carne;
    private OpcionEnsalada ensalada;
    private OpcionJugo jugo;
    
    public Corrientazo(Integer precio, OpcionSopa sopa, OpcionPrincipio principio, OpcionCarne carne, OpcionJugo jugo) {
        this.precio = precio;
        this.sopa = sopa;
        this.principio = principio;
        this.carne = carne;
        this.jugo = jugo;
    }

    public Corrientazo(Integer precio, OpcionSopa sopa, OpcionPrincipio principio, OpcionCarne carne,
            OpcionEnsalada ensalada, OpcionJugo jugo) {
        this.precio = precio;
        this.sopa = sopa;
        this.principio = principio;
        this.carne = carne;
        this.ensalada = ensalada;
        this.jugo = jugo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public OpcionSopa getSopa() {
        return sopa;
    }

    public void setSopa(OpcionSopa sopa) {
        this.sopa = sopa;
    }

    public OpcionPrincipio getPrincipio() {
        return principio;
    }

    public void setPrincipio(OpcionPrincipio principio) {
        this.principio = principio;
    }

    public OpcionCarne getCarne() {
        return carne;
    }

    public void setCarne(OpcionCarne carne) {
        this.carne = carne;
    }

    public OpcionEnsalada getEnsalada() {
        return ensalada;
    }

    public void setEnsalada(OpcionEnsalada ensalada) {
        this.ensalada = ensalada;
    }

    public OpcionJugo getJugo() {
        return jugo;
    }

    public void setJugo(OpcionJugo jugo) {
        this.jugo = jugo;
    }

    @Override
    public String toString() {
        return "Corrientazo [precio=" + precio + ", sopa=" + sopa + ", principio=" + principio + ", carne=" + carne + ", ensalada=" + ensalada + ", jugo=" + jugo + "]";
    }

    

}
