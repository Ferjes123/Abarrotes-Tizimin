package com.example.Abarrotes.interfaces;

import java.util.List;

public interface Persistente<T> {
    void guardar(List<T> lista, String rutaArchivo) throws Exception;
    List<T> cargar(String rutaArchivo) throws Exception;
}
