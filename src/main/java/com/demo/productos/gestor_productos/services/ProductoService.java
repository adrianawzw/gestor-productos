package com.demo.productos.gestor_productos.services;

import java.util.List;
import java.util.Optional;

import com.demo.productos.gestor_productos.DTOs.ProductoCreateDTO;
import com.demo.productos.gestor_productos.DTOs.ProductoDTO;
import com.demo.productos.gestor_productos.entities.EstadoProducto;

public interface ProductoService {
    ProductoDTO registrarProducto(ProductoCreateDTO dto);

    List<ProductoDTO> listarProductos();

    Optional<ProductoDTO> buscarPorNombre(String nombre);

    Optional<ProductoDTO> buscarPorId(Long idProducto);

    ProductoDTO actualizarProducto(Long idProducto, ProductoCreateDTO dto);

    void eliminarProducto(Long idProducto);

    ProductoDTO cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto);

    List<ProductoDTO> obtenerProductosPorEstado(EstadoProducto estadoProducto);
}
