package com.demo.productos.gestor_productos.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.productos.gestor_productos.DTOs.ProductoCreateDTO;
import com.demo.productos.gestor_productos.DTOs.ProductoDTO;
import com.demo.productos.gestor_productos.entities.Producto;

@Component
public class ProductoMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    // DTO de entrada → Entity
    public Producto toEntity(ProductoCreateDTO dto) {
        return modelMapper.map(dto, Producto.class);
    }

    // Entity → DTO de salida
    public ProductoDTO toDTO(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);
    }
}
