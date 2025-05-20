package com.example.Abarrotes.interfaces;

import java.util.List;

public interface Gestionable<T> {
    boolean agregar(T obj);
    boolean eliminar(String nombre);
    boolean modificar(String nombre, T nuevoObjeto);
    T buscar(String nombre);
    List<T> listar();
}
