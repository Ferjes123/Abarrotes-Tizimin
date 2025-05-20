package com.example.Abarrotes.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainController {
    @FXML
    private Label labelBienvenida;

    @FXML
    private void salir() {
        System.exit(0);
    }

    @FXML
    private void abrirClientes() {
        abrirVentana("/vista/ClientesView.fxml", "Gestión de Clientes");
    }

    @FXML
    private void abrirInventario() {
        abrirVentana("/vista/InventarioView.fxml", "Inventario");
    }

    @FXML
    private void abrirCompras() {
        abrirVentana("/vista/ComprasView.fxml", "Registrar Compras");
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
