package com.example.Abarrotes.modelo;

import com.example.Abarrotes.interfaces.Imprimible;
import java.time.format.DateTimeFormatter;

public class Ticket implements Imprimible {
    private Compra compra;

    public Ticket(Compra compra) {
        this.compra = compra;
    }

    @Override
    public String generarSalida() {
        StringBuilder sb = new StringBuilder();
        sb.append("********** TICKET DE COMPRA **********\n");
        sb.append("Compra ID: ").append(compra.getIdCompra()).append("\n");
        sb.append("Cliente: ").append(compra.getCliente().getNombre()).append(" ").append(compra.getCliente().getApellido()).append("\n");
        sb.append("Fecha: ").append(compra.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        sb.append("-------------------------------------\n");
        sb.append("Producto          Cantidad   Subtotal\n");
        for (DetalleCompra d : compra.getDetalles()) {
            sb.append(String.format("%-17s %7d %10.2f\n", d.getProducto().getNombre(), d.getCantidad(), d.getSubtotal()));
        }
        sb.append("-------------------------------------\n");
        sb.append(String.format("TOTAL: %.2f\n", compra.calcularTotal()));
        sb.append("Gracias por su compra!\n");
        return sb.toString();
    }
}
