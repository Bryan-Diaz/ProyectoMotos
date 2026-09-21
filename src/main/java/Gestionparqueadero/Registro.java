package gestion parqueadero;

import Gestion motos.moto;
import Gestion pagos.Pago;
import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private moto moto;
    private espacio espacio;
    private Pago pago;

    // Constructor
    public Registro(moto moto, espacio espacio) {
        this.moto = moto;
        this.espacio = espacio;
        this.fechaHoraIngreso = LocalDateTime.now();
    }

    // Métodos del diagrama
    public double calcularValor(double tarifa) {
        if (this.fechaHoraSalida == null) {
            this.fechaHoraSalida = LocalDateTime.now();
        }
        long minutos = Duration.between(this.fechaHoraIngreso, this.fechaHoraSalida).toMinutes();
        if (minutos <= 0) {
            minutos = 1;
        }
        return minutos * tarifa;
    }

    public void registrarIngreso() {
        if (this.espacio != null && this.moto != null) {
            this.espacio.ocupar();
            System.out.println("Ingreso registrado: Moto " + this.moto.consultarPlaca() + " en Espacio #" + this.espacio.getNumero());
        }
    }

    public void registrarSalida() {
        if (this.espacio != null && this.moto != null) {
            this.espacio.liberar();
            System.out.println("Salida registrada: Moto " + this.moto.consultarPlaca() + " del Espacio #" + this.espacio.getNumero());
        }
    }

    // Encapsulamiento (Getters y Setters)
    public LocalDateTime getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public moto getMoto() {
        return moto;
    }

    public void setMoto(moto moto) {
        this.moto = moto;
    }

    public espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(espacio espacio) {
        this.espacio = espacio;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}