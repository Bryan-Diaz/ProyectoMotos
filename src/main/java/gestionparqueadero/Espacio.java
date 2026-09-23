package gestionparqueadero;

public class Espacio {
    private int numero;
    private boolean estado; // false = Libre, true = Ocupado

    // Constructor
    public Espacio(int numero) {
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

    // Encapsulamiento en español (Obtener y Modificar)
    public int obtenerNumero() {
        return numero;
    }

    public void modificarNumero(int numero) {
        this.numero = numero;
    }

    public boolean obtenerEstado() {
        return estado;
    }

    public void modificarEstado(boolean estado) {
        this.estado = estado;
    }
}