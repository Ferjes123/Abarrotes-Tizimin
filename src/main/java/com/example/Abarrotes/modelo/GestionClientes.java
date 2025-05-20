package com.example.Abarrotes.modelo;

import com.example.Abarrotes.interfaces.Gestionable;
import java.util.ArrayList;
import java.util.List;

public class GestionClientes implements Gestionable<Cliente> {
    private List<Cliente> listaClientes = new ArrayList<>();

    @Override
    public boolean agregar(Cliente cliente) {
        if (buscar(cliente.getNombre()) != null) return false;
        listaClientes.add(cliente);
        return true;
    }

    @Override
    public boolean eliminar(String nombre) {
        Cliente c = buscar(nombre);
        if (c != null) {
            listaClientes.remove(c);
            return true;
        }
        return false;
    }

    @Override
    public boolean modificar(String nombre, Cliente nuevoCliente) {
        Cliente c = buscar(nombre);
        if (c != null) {
            c.setNombre(nuevoCliente.getNombre());
            c.setApellido(nuevoCliente.getApellido());
            c.setDireccion(nuevoCliente.getDireccion());
            c.setTelefono(nuevoCliente.getTelefono());
            return true;
        }
        return false;
    }

    @Override
    public Cliente buscar(String nombre) {
        for (Cliente c : listaClientes)
            if (c.getNombre().equalsIgnoreCase(nombre)) return c;
        return null;
    }

    @Override
    public List<Cliente> listar() {
        return listaClientes;
    }
}
