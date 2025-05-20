package com.example.Abarrotes.modelo;

public class ClienteBuilder {
    private String nombre, apellido, telefono;
    private Direccion direccion;

    public ClienteBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ClienteBuilder setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public ClienteBuilder setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ClienteBuilder setDireccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public Cliente build() {
        return new Cliente(0, nombre, apellido, direccion, telefono);
    }
}
