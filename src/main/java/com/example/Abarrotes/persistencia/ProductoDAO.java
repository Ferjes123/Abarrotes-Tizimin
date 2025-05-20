package com.example.Abarrotes.persistencia;

import com.example.Abarrotes.interfaces.Persistente;
import com.example.Abarrotes.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements Persistente<Producto> {

    @Override
    public void guardar(List<Producto> lista, String rutaArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Producto p : lista) {
                bw.write(p.toFileString());
                bw.newLine();
            }
        }
    }

    @Override
    public List<Producto> cargar(String rutaArchivo) throws IOException {
        List<Producto> productos = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) return productos;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Producto p = Producto.fromFileString(linea);
                if (p != null) productos.add(p);
            }
        }
        return productos;
    }
}
