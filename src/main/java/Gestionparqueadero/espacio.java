package Gestionparqueadero;

public class espacio {
    private int numero;
    private boolean estado; // false = Libre, true = Ocupado

    // Constructor
    public espacio(int numero) {
        this.numero = numero;
        this.estado = false;
    }

    // Métodos del diagrama
    public void ocupar() {
        this.estado = true;
    }

    public void liberar() {
        this.estado = false;
    }

    // Encapsulamiento (Getters y Setters)
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}