package com.example.Abarrotes.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.Abarrotes.modelo.AbarrotesTizimin;
import com.example.Abarrotes.modelo.Producto;
import com.example.Abarrotes.modelo.ProductoFactory;

public class InventarioController {

    @FXML private TextField txtNombre, txtPrecioProveedor, txtPrecioPublico, txtCantidad;
    @FXML private ListView<String> listProductos;

    private ObservableList<String> productosObservable;

    @FXML
    public void initialize() {
        actualizarLista();

        listProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                String nombre = newSel.split(" \\| ")[0];
                Producto p = AbarrotesTizimin.getInstancia().getInventario().buscar(nombre);
                if (p != null) {
                    txtNombre.setText(p.getNombre());
                    txtPrecioProveedor.setText(String.valueOf(p.getPrecioProveedor()));
                    txtPrecioPublico.setText(String.valueOf(p.getPrecioPublico()));
                    txtCantidad.setText(String.valueOf(p.getCantidadExistencia()));
                }
            }
        });
    }

    @FXML
    private void agregarProducto() {
        try {
            String nombre = txtNombre.getText();
            double precioProv = Double.parseDouble(txtPrecioProveedor.getText());
            double precioPub = Double.parseDouble(txtPrecioPublico.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            Producto nuevo = ProductoFactory.crearProducto(nombre, precioProv, precioPub, cantidad);
            boolean agregado = AbarrotesTizimin.getInstancia().getInventario().agregar(nuevo);

            if (agregado) {
                mostrarMensaje("Producto agregado.");
                actualizarLista();
                limpiarCampos();
            } else {
                mostrarMensaje("Ya existe un producto con ese nombre.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Verifica los datos ingresados.");
        }
    }

    @FXML
    private void modificarProducto() {
        String seleccionado = listProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje("Selecciona un producto primero.");
            return;
        }
        String nombreOriginal = seleccionado.split(" \\| ")[0];

        String nombre = txtNombre.getText().trim();
        String strPrecioProv = txtPrecioProveedor.getText().trim();
        String strPrecioPub = txtPrecioPublico.getText().trim();
        String strCantidad = txtCantidad.getText().trim();

        if (nombre.isEmpty() || strPrecioProv.isEmpty() || strPrecioPub.isEmpty() || strCantidad.isEmpty()) {
            mostrarMensaje("Completa todos los campos.");
            return;
        }

        try {
            double precioProv = Double.parseDouble(strPrecioProv);
            double precioPub = Double.parseDouble(strPrecioPub);
            int cantidad = Integer.parseInt(strCantidad);

            Producto nuevo = ProductoFactory.crearProducto(nombre, precioProv, precioPub, cantidad);
            boolean modificado = AbarrotesTizimin.getInstancia().getInventario().modificar(nombreOriginal, nuevo);

            if (modificado) {
                mostrarMensaje("Producto modificado.");
                actualizarLista();
                limpiarCampos();
            } else {
                mostrarMensaje("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Los valores de precio y cantidad deben ser números válidos.");
        }
    }

    @FXML
    private void eliminarProducto() {
        String nombre = txtNombre.getText();
        boolean eliminado = AbarrotesTizimin.getInstancia().getInventario().eliminar(nombre);
        if (eliminado) {
            mostrarMensaje("Producto eliminado.");
            actualizarLista();
            limpiarCampos();
        } else {
            mostrarMensaje("Producto no encontrado.");
        }
    }

    private void actualizarLista() {
        productosObservable = FXCollections.observableArrayList();
        for (Producto p : AbarrotesTizimin.getInstancia().getInventario().listar()) {
            productosObservable.add(p.getNombre() + " | $" + p.getPrecioPublico() + " | Cant: " + p.getCantidadExistencia());
        }
        listProductos.setItems(productosObservable);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtPrecioProveedor.clear();
        txtPrecioPublico.clear();
        txtCantidad.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
