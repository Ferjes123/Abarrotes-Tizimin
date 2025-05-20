package com.example.Abarrotes.modelo;

import com.example.Abarrotes.persistencia.ClienteDAO;
import com.example.Abarrotes.persistencia.ProductoDAO;

import java.io.IOException;
import java.util.List;

public class AbarrotesTizimin {
    private static AbarrotesTizimin instancia;
    private GestionClientes gestionClientes = new GestionClientes();
    private Inventario inventario = new Inventario();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    private final String archivoClientes = "clientes.txt";
    private final String archivoProductos = "productos.txt";

    private AbarrotesTizimin() {
        cargarDatos();
    }

    public static AbarrotesTizimin getInstancia() {
        if (instancia == null) instancia = new AbarrotesTizimin();
        return instancia;
    }

    public GestionClientes getGestionClientes() { return gestionClientes; }
    public Inventario getInventario() { return inventario; }

    public void guardarDatos() {
        try {
            clienteDAO.guardar(gestionClientes.listar(), archivoClientes);
            productoDAO.guardar(inventario.listar(), archivoProductos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        try {
            for (Cliente c : clienteDAO.cargar(archivoClientes)) gestionClientes.agregar(c);
            for (Producto p : productoDAO.cargar(archivoProductos)) inventario.agregar(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
