package com.example.Abarrotes.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compra {
    private static int contadorId = 1;
    private int idCompra;
    private Cliente cliente;
    private List<DetalleCompra> detalles = new ArrayList<>();
    private LocalDateTime fechaHora;

    public Compra(Cliente cliente) {
        this.idCompra = contadorId++;
        this.cliente = cliente;
        this.fechaHora = LocalDateTime.now();
    }

    public int getIdCompra() { return idCompra; }
    public Cliente getCliente() { return cliente; }
    public List<DetalleCompra> getDetalles() { return detalles; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    public void agregarDetalle(DetalleCompra detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal() {
        return detalles.stream().mapToDouble(DetalleCompra::getSubtotal).sum();
    }
}
