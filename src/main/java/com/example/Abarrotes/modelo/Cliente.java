package com.example.Abarrotes.modelo;

import com.example.Abarrotes.interfaces.Identificable;

public class Cliente implements Identificable {
    private static int contadorId = 1;
    private int id;
    private String nombre, apellido, telefono;
    private Direccion direccion;

    public Cliente(int id, String nombre, String apellido, Direccion direccion, String telefono) {
        if (id == 0) {
            this.id = contadorId++;
        } else {
            this.id = id;
            if (id >= contadorId) contadorId = id + 1;
        }
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public Direccion getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return id + "|" + nombre + "|" + apellido + "|" + direccion.toString() + "|" + telefono;
    }

    public String toFileString() {
        return id + "|" + nombre + "|" + apellido + "|" +
                direccion.getCalle() + "|" + direccion.getNumero() + "|" + direccion.getColonia() + "|" +
                direccion.getCp() + "|" + direccion.getCiudad() + "|" + direccion.getEstado() + "|" + telefono;

    }

    public static Cliente fromFileString(String linea) {
        try {
            String[] p = linea.split("\\|");
            return new Cliente(Integer.parseInt(p[0]), p[1], p[2],
                    new Direccion(p[3], p[4], p[5], p[6], p[7], p[8]), p[9]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
