package com.example.Abarrotes.modelo;

import com.example.Abarrotes.interfaces.Identificable;

public class Producto implements Identificable {
    private static int contadorId = 1;
    private int idProducto;
    private String nombre;
    private double precioPublico, precioProveedor;
    private int cantidadExistencia;

    public Producto(int id, String nombre, double precioProveedor, double precioPublico, int cantidad) {
        if (id == 0) this.idProducto = contadorId++;
        else {
            this.idProducto = id;
            if (id >= contadorId) contadorId = id + 1;
        }
        this.nombre = nombre;
        this.precioProveedor = precioProveedor;
        this.precioPublico = precioPublico;
        this.cantidadExistencia = cantidad;
    }

    public int getId() { return idProducto; }
    public String getNombre() { return nombre; }
    public double getPrecioPublico() { return precioPublico; }
    public double getPrecioProveedor() { return precioProveedor; }
    public int getCantidadExistencia() { return cantidadExistencia; }

    public void aumentarCantidad(int cantidad) { cantidadExistencia += cantidad; }

    public void disminuirCantidad(int cantidad) {
        if (cantidadExistencia >= cantidad) cantidadExistencia -= cantidad;
    }

    public void actualizarDatos(String nombre, double precioPub, double precioProv) {
        this.nombre = nombre;
        this.precioPublico = precioPub;
        this.precioProveedor = precioProv;
    }

    public String toFileString() {
        return idProducto + "|" + nombre + "|" + precioProveedor + "|" + precioPublico + "|" + cantidadExistencia;
    }

    public static Producto fromFileString(String linea) {
        try {
            String[] p = linea.split("\\|");
            return new Producto(Integer.parseInt(p[0]), p[1],
                    Double.parseDouble(p[2]), Double.parseDouble(p[3]), Integer.parseInt(p[4]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
