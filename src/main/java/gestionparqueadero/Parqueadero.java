package gestionparqueadero;

import gestionmotos.Moto;
import gestionpagos.Pago;
import gestionpagos.TipoPago;

import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    private int capacidad;
    private double tarifa;
    private List<Espacio> espacios;
    private List<Registro> registrosActivos;
    private List<Registro> historialRegistros;

    private double totalNequi;
    private double totalEfectivo;

    // Constructor
    public Parqueadero() {
        this.capacidad = 23;
        this.tarifa = 40.0;
        this.espacios = new ArrayList<>();
        this.registrosActivos = new ArrayList<>();
        this.historialRegistros = new ArrayList<>();
        this.totalNequi = 0.0;
        this.totalEfectivo = 0.0;

        for (int i = 1; i <= this.capacidad; i++) {
            this.espacios.add(new Espacio(i));
        }
    }

    public boolean registrarIngreso(Moto moto) {
        Espacio espacioLibre = buscarEspacioDisponible();
        if (espacioLibre == null) {
            return false;
        }

        Registro nuevoRegistro = new Registro(moto, espacioLibre);
        nuevoRegistro.registrarIngreso();
        this.registrosActivos.add(nuevoRegistro);
        return true;
    }

    public String registrarSalida(String placa, TipoPago tipoPago) {
        Registro registroEncontrado = null;

        for (Registro r : this.registrosActivos) {
            if (r.obtenerMoto().obtenerPlaca().equalsIgnoreCase(placa)) {
                registroEncontrado = r;
                break;
            }
        }

        if (registroEncontrado == null) {
            return "No se encontró una moto activa con la placa: " + placa;
        }

        double valorPagar = registroEncontrado.calcularValor(this.tarifa);
        Pago pago = new Pago(valorPagar, tipoPago);

        if (pago.validarPago()) {
            pago.registrarPago();
            registroEncontrado.modificarPago(pago);
            registroEncontrado.registrarSalida();

            // Sumar al total según el tipo de pago utilizado
            if (tipoPago == TipoPago.NEQUI) {
                this.totalNequi += valorPagar;
            } else if (tipoPago == TipoPago.EFECTIVO) {
                this.totalEfectivo += valorPagar;
            }

            this.historialRegistros.add(registroEncontrado);
            this.registrosActivos.remove(registroEncontrado);
            return "Salida exitosa. Total a pagar: $" + valorPagar;
        } else {
            return "Error al procesar el pago.";
        }
    }

    public double generarReporteDia() {
        return this.totalNequi + this.totalEfectivo;
    }

    public double obtenerTotalNequi() {
        return this.totalNequi;
    }

    public double obtenerTotalEfectivo() {
        return this.totalEfectivo;
    }

    public int obtenerTotalMotosIngresadas() {
        return this.registrosActivos.size() + this.historialRegistros.size();
    }

    public int obtenerTotalMotosSalidas() {
        return this.historialRegistros.size();
    }

    public int consultarEspacios() {
        int disponibles = 0;
        for (Espacio e : this.espacios) {
            if (!e.obtenerEstado()) {
                disponibles++;
            }
        }
        return disponibles;
    }

    private Espacio buscarEspacioDisponible() {
        for (Espacio e : this.espacios) {
            if (!e.obtenerEstado()) {
                return e;
            }
        }
        return null;
    }

    public int obtenerCapacidad() { return capacidad; }
    public void modificarCapacidad(int capacidad) { this.capacidad = capacidad; }
    public double obtenerTarifa() { return tarifa; }
    public void modificarTarifa(double tarifa) { this.tarifa = tarifa; }
    public List<Espacio> obtenerEspacios() { return espacios; }
    public void modificarEspacios(List<Espacio> espacios) { this.espacios = espacios; }
    public List<Registro> obtenerRegistrosActivos() { return registrosActivos; }
    public void modificarRegistrosActivos(List<Registro> registrosActivos) { this.registrosActivos = registrosActivos; }
}