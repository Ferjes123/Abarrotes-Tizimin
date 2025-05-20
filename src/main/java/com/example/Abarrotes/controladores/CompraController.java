package com.example.Abarrotes.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.Abarrotes.modelo.*;

import java.util.ArrayList;
import java.util.List;

public class CompraController {

    @FXML private ComboBox<String> comboClientes;
    @FXML private ComboBox<String> comboProductos;
    @FXML private TextField txtCantidad;
    @FXML private ListView<String> listCarrito;
    @FXML private TextArea txtTicket;

    private List<DetalleCompra> carrito = new ArrayList<>();
    private ObservableList<String> carritoObservable;

    @FXML
    public void initialize() {
        for (Cliente c : AbarrotesTizimin.getInstancia().getGestionClientes().listar()) {
            comboClientes.getItems().add(c.getNombre());
        }
        for (Producto p : AbarrotesTizimin.getInstancia().getInventario().listar()) {
            comboProductos.getItems().add(p.getNombre());
        }
        carritoObservable = FXCollections.observableArrayList();
        listCarrito.setItems(carritoObservable);
    }

    @FXML
    private void agregarAlCarrito() {
        String nombreProducto = comboProductos.getValue();
        String cantidadTexto = txtCantidad.getText();

        if (nombreProducto == null || cantidadTexto.isEmpty()) {
            mostrarMensaje("Selecciona un producto y cantidad.");
            return;
        }

        Producto producto = AbarrotesTizimin.getInstancia().getInventario().buscar(nombreProducto);
        int cantidadNueva = Integer.parseInt(cantidadTexto);

        // 👇 Calcular cuántas unidades de este producto ya están en el carrito
        int cantidadEnCarrito = 0;
        for (DetalleCompra d : carrito) {
            if (d.getProducto().getNombre().equalsIgnoreCase(nombreProducto)) {
                cantidadEnCarrito += d.getCantidad();
            }
        }

        int totalDeseado = cantidadEnCarrito + cantidadNueva;

        if (producto.getCantidadExistencia() < totalDeseado) {
            mostrarMensaje("Stock insuficiente. Disponibles: " + producto.getCantidadExistencia() +
                    ", ya añadiste: " + cantidadEnCarrito);
            return;
        }

        // Agregar al carrito
        carrito.add(new DetalleCompra(producto, cantidadNueva));
        carritoObservable.add(nombreProducto + " x" + cantidadNueva + " = $" + (producto.getPrecioPublico() * cantidadNueva));
    }


    @FXML
    private void finalizarCompra() {
        String nombreCliente = comboClientes.getValue();
        if (nombreCliente == null || carrito.isEmpty()) {
            mostrarMensaje("Selecciona un cliente y agrega productos.");
            return;
        }

        Cliente cliente = AbarrotesTizimin.getInstancia().getGestionClientes().buscar(nombreCliente);
        Compra compra = new Compra(cliente);

        for (DetalleCompra detalle : carrito) {
            Producto p = detalle.getProducto();
            int cantidad = detalle.getCantidad();

            p.disminuirCantidad(cantidad);
            compra.agregarDetalle(detalle);
        }

        Ticket ticket = new Ticket(compra);
        txtTicket.setText(ticket.generarSalida());

        carrito.clear();
        carritoObservable.clear();
        AbarrotesTizimin.getInstancia().guardarDatos();
    }

    private void mostrarMensaje(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(texto);
        alert.showAndWait();
    }
}
