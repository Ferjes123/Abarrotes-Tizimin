package com.example.Abarrotes.modelo;

import com.example.Abarrotes.interfaces.Gestionable;
import java.util.ArrayList;
import java.util.List;

public class Inventario implements Gestionable<Producto> {
    private List<Producto> listaArticulos = new ArrayList<>();

    @Override
    public boolean agregar(Producto producto) {
        if (buscar(producto.getNombre()) != null) return false;
        listaArticulos.add(producto);
        return true;
    }

    @Override
    public boolean eliminar(String nombre) {
        Producto p = buscar(nombre);
        if (p != null) {
            listaArticulos.remove(p);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificar(String nombre, Producto nuevoProducto) {
        Producto p = buscar(nombre);
        if (p != null) {
            p.actualizarDatos(nuevoProducto.getNombre(), nuevoProducto.getPrecioPublico(), nuevoProducto.getPrecioProveedor());
            p.aumentarCantidad(nuevoProducto.getCantidadExistencia() - p.getCantidadExistencia());
            return true;
        }
        return false;
    }

    @Override
    public Producto buscar(String nombre) {
        for (Producto p : listaArticulos)
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        return null;
    }

    @Override
    public List<Producto> listar() {
        return listaArticulos;
    }
}
