package gestionpagos;

import java.time.LocalDateTime;

public class Pago {
    private double valor;
    private TipoPago tipo;
    private LocalDateTime fechaHora;

    // Constructor
    public Pago(double valor, TipoPago tipo) {
        this.valor = valor;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
    }

    public void registrarPago() {
        System.out.println("Pago registrado por $" + this.valor + " mediante " + this.tipo);
    }

    public boolean validarPago() {
        return this.valor > 0 && this.tipo != null;
    }

    public double obtenerValor() {
        return valor;
    }

    public void modificarValor(double valor) {
        this.valor = valor;
    }

    public TipoPago obtenerTipo() {
        return tipo;
    }

    public void modificarTipo(TipoPago tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime obtenerFechaHora() {
        return fechaHora;
    }

    public void modificarFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}