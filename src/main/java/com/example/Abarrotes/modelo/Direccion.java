package com.example.Abarrotes.modelo;

public class Direccion {
    private String calle, numero, colonia, cp, ciudad, estado;

    public Direccion(String calle, String numero, String colonia, String cp, String ciudad, String estado) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return calle + " #" + numero + ", " + colonia + ", CP: " + cp + ", " + ciudad + ", " + estado;
    }
    public String getCalle() { return calle; }
    public String getNumero() { return numero; }
    public String getColonia() { return colonia; }
    public String getCp() { return cp; }
    public String getCiudad() { return ciudad; }
    public String getEstado() { return estado; }


}
