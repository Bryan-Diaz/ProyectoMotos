package Gestionmotos;

public class moto {
    private String placa;
    private String marca;
    private String color;

    // Constructor
    public moto(String placa, String marca, String color) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
    }

    // Método expuesto en el diagrama
    public String consultarPlaca() {
        return this.placa;
    }

    // Encapsulamiento (Getters y Setters)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}