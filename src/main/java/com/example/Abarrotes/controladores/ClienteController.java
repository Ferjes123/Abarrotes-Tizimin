package com.example.Abarrotes.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.Abarrotes.modelo.AbarrotesTizimin;
import com.example.Abarrotes.modelo.Cliente;
import com.example.Abarrotes.modelo.ClienteBuilder;
import com.example.Abarrotes.modelo.Direccion;

public class ClienteController {

    @FXML private TextField txtNombre, txtApellido, txtCalle, txtNumero, txtColonia, txtCP, txtCiudad, txtEstado, txtTelefono;
    @FXML private ListView<String> listClientes;
    @FXML private TextArea txtDetalleCliente;

    private ObservableList<String> clientesObservable;

    @FXML
    public void initialize() {
        listClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> mostrarDetallesCliente(newVal));
        actualizarLista();
    }

    @FXML
    private void agregarCliente() {
        if (!validarCampos()) return;

        Direccion direccion = new Direccion(
                txtCalle.getText().trim(),
                txtNumero.getText().trim(),
                txtColonia.getText().trim(),
                txtCP.getText().trim(),
                txtCiudad.getText().trim(),
                txtEstado.getText().trim()
        );

        Cliente cliente = new ClienteBuilder()
                .setNombre(txtNombre.getText().trim())
                .setApellido(txtApellido.getText().trim())
                .setDireccion(direccion)
                .setTelefono(txtTelefono.getText().trim())
                .build();

        boolean agregado = AbarrotesTizimin.getInstancia().getGestionClientes().agregar(cliente);
        if (agregado) {
            actualizarLista();
            limpiarCampos();
            mostrarMensaje("Cliente agregado.");
        } else {
            mostrarMensaje("Ya existe un cliente con ese nombre.");
        }
    }

    @FXML
    private void eliminarCliente() {
        String seleccionado = listClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje("Seleccione un cliente para eliminar.");
            return;
        }
        String nombre = seleccionado.split(" ")[0];
        boolean eliminado = AbarrotesTizimin.getInstancia().getGestionClientes().eliminar(nombre);
        if (eliminado) {
            actualizarLista();
            txtDetalleCliente.clear();
            mostrarMensaje("Cliente eliminado.");
        } else {
            mostrarMensaje("No se pudo eliminar el cliente.");
        }
    }

    private void actualizarLista() {
        clientesObservable = FXCollections.observableArrayList();
        for (Cliente c : AbarrotesTizimin.getInstancia().getGestionClientes().listar()) {
            clientesObservable.add(c.getNombre() + " " + c.getApellido());
        }
        listClientes.setItems(clientesObservable);
    }

    private void mostrarDetallesCliente(String nombreCompleto) {
        if (nombreCompleto == null) {
            txtDetalleCliente.clear();
            return;
        }

        String nombre = nombreCompleto.split(" ")[0];
        Cliente cliente = AbarrotesTizimin.getInstancia().getGestionClientes().buscar(nombre);

        if (cliente != null) {
            Direccion d = cliente.getDireccion();
            StringBuilder sb = new StringBuilder();
            sb.append("ID: ").append(cliente.getId()).append("\n");
            sb.append("Nombre: ").append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n");
            sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n");
            sb.append("Dirección:\n");
            sb.append("  Calle: ").append(d.getCalle()).append(" ").append(d.getNumero()).append("\n");
            sb.append("  Colonia: ").append(d.getColonia()).append("\n");
            sb.append("  CP: ").append(d.getCp()).append("\n");
            sb.append("  Ciudad: ").append(d.getCiudad()).append("\n");
            sb.append("  Estado: ").append(d.getEstado()).append("\n");

            txtDetalleCliente.setText(sb.toString());
        } else {
            txtDetalleCliente.setText("No se encontró información.");
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCalle.clear();
        txtNumero.clear();
        txtColonia.clear();
        txtCP.clear();
        txtCiudad.clear();
        txtEstado.clear();
        txtTelefono.clear();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean validarCampos() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String calle = txtCalle.getText().trim();
        String numero = txtNumero.getText().trim();
        String colonia = txtColonia.getText().trim();
        String cp = txtCP.getText().trim();
        String ciudad = txtCiudad.getText().trim();
        String estado = txtEstado.getText().trim();

        if (nombre.isEmpty() || !nombre.matches("[a-zA-Z ]+")) {
            mostrarAlerta("Error en Nombre", "El nombre es obligatorio y solo debe contener letras y espacios.");
            return false;
        }
        if (apellido.isEmpty() || !apellido.matches("[a-zA-Z ]+")) {
            mostrarAlerta("Error en Apellido", "El apellido es obligatorio y solo debe contener letras y espacios.");
            return false;
        }
        if (telefono.isEmpty() || !telefono.matches("\\+?\\d+")) {
            mostrarAlerta("Error en Teléfono", "El teléfono es obligatorio y solo debe contener números (puede iniciar con '+').");
            return false;
        }
        if (calle.isEmpty() || numero.isEmpty() || colonia.isEmpty() || cp.isEmpty() || ciudad.isEmpty() || estado.isEmpty()) {
            mostrarAlerta("Error en Dirección", "Todos los campos de dirección son obligatorios.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
