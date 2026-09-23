package gestionparqueadero;

import gestionmotos.Moto;
import gestionpagos.Pago;
import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime fechaHoraIngreso;
    private LocalDateTime fechaHoraSalida;
    private Moto moto;
    private Espacio espacio;
    private Pago pago;

    // Constructor
    public Registro(Moto moto, Espacio espacio) {
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
            System.out.println("Ingreso registrado: Moto " + this.moto.consultarPlaca() + " en Espacio #" + this.espacio.obtenerNumero());
        }
    }

    public void registrarSalida() {
        if (this.espacio != null && this.moto != null) {
            this.espacio.liberar();
            System.out.println("Salida registrada: Moto " + this.moto.consultarPlaca() + " del Espacio #" + this.espacio.obtenerNumero());
        }
    }

    // Encapsulamiento en español (Obtener y Modificar)
    public LocalDateTime obtenerFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void modificarFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public LocalDateTime obtenerFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void modificarFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public Moto obtenerMoto() {
        return moto;
    }

    public void modificarMoto(Moto moto) {
        this.moto = moto;
    }

    public Espacio obtenerEspacio() {
        return espacio;
    }

    public void modificarEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    public Pago obtenerPago() {
        return pago;
    }

    public void modificarPago(Pago pago) {
        this.pago = pago;
    }
}