package com.example.Abarrotes.persistencia;

import com.example.Abarrotes.interfaces.Persistente;
import com.example.Abarrotes.modelo.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements Persistente<Cliente> {

    @Override
    public void guardar(List<Cliente> lista, String rutaArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Cliente c : lista) {
                bw.write(c.toFileString());
                bw.newLine();
            }
        }
    }

    @Override
    public List<Cliente> cargar(String rutaArchivo) throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) return clientes;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Cliente c = Cliente.fromFileString(linea);
                if (c != null) clientes.add(c);
            }
        }
        return clientes;
    }
}
