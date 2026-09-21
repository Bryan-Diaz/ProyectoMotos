package Gestionpagos;

import java.time.LocalDateTime;

public class pago {
    private double valor;
    private Tipopago tipo;
    private LocalDateTime fechaHora;

    // Constructor
    public Pago(double valor, Tipopago tipo) {
        this.valor = valor;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
    }

    // Métodos del diagrama
    public void registrarPago() {
        System.out.println("Pago registrado por $" + this.valor + " mediante " + this.tipo);
    }

    public boolean validarPago() {
        return this.valor > 0 && this.tipo != null;
    }

    // Encapsulamiento (Getters y Setters)
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Tipopago getTipo() {
        return tipo;
    }

    public void setTipo(Tipopago tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}