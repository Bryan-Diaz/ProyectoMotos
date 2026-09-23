package gestionmotos;

public class Moto {
    private String placa;
    private String marca;
    private String cedula;

    public Moto(String placa, String marca, String cedula) {
        this.placa = placa;
        this.marca = marca;
        this.cedula = cedula;
    }

    public String consultarPlaca() {
        return this.placa;
    }

    public String obtenerPlaca() {
        return placa;
    }

    public void modificarPlaca(String placa) {
        this.placa = placa;
    }

    public String obtenerMarca() {
        return marca;
    }

    public void modificarMarca(String marca) {
        this.marca = marca;
    }

    public String obtenerCedula() {
        return cedula;
    }

    public void modificarCedula(String cedula) {
        this.cedula = cedula;
    }
}