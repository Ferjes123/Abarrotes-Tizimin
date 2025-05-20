package com.example.Abarrotes.modelo;

public class ProductoFactory {
    public static Producto crearProducto(String nombre, double precioProveedor, double precioPublico, int cantidad) {
        return new Producto(0, nombre, precioProveedor, precioPublico, cantidad);
    }
}
