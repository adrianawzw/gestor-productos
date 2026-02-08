package com.demo.productos.gestor_productos.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos para crear un producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDTO {

    @Schema(example = "Laptop")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreProducto;

    @Schema(example = "Laptop gamer")
    private String descripcion;

    @Schema(example = "2500")
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;
    
    @Schema(example = "10")
    private int cantidad;
}
