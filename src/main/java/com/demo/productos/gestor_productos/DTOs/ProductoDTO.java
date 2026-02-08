package com.demo.productos.gestor_productos.DTOs;

import com.demo.productos.gestor_productos.entities.EstadoProducto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO de respuesta que representa un producto")
public class ProductoDTO {
    @Schema(description = "ID único del producto", example = "1")
    private Long idProducto;

    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    private String nombreProducto;

    @Schema(description = "Descripción del producto", example = "Laptop con RTX 4060")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "2499.99")
    private Double precio;

    @Schema(description = "Cantidad disponible", example = "10")
    private int cantidad;

    @Schema(description = "Estado actual del producto", example = "ACTIVO")
    private EstadoProducto estadoProducto;
}
