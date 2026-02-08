package com.demo.productos.gestor_productos.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.productos.gestor_productos.DTOs.ProductoCreateDTO;
import com.demo.productos.gestor_productos.DTOs.ProductoDTO;
import com.demo.productos.gestor_productos.entities.EstadoProducto;
import com.demo.productos.gestor_productos.entities.Producto;
import com.demo.productos.gestor_productos.exceptions.BadRequestException;
import com.demo.productos.gestor_productos.exceptions.ResourceNotFoundException;
import com.demo.productos.gestor_productos.mappers.ProductoMapper;
import com.demo.productos.gestor_productos.repositories.ProductoRepository;
import com.demo.productos.gestor_productos.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO dto) {
        
        productoRepository.findByNombreProducto(dto.getNombreProducto())
                .ifPresent(p -> {
                    throw new BadRequestException("Ya existe un producto con ese nombre");
                });
        
        Producto producto = productoMapper.toEntity(dto);
        producto.setEstadoProducto(EstadoProducto.DISPONIBLE);

        Producto guardado = productoRepository.save(producto);
        return productoMapper.toDTO(guardado);
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .toList();    
    }

    @Override
    public Optional<ProductoDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreProducto(nombre)
        .map(productoMapper::toDTO);    
    }

    @Override
    public Optional<ProductoDTO> buscarPorId(Long idProducto) {
        return productoRepository.findById(idProducto)
                .map(productoMapper::toDTO);    
    }

    @Override
    public ProductoDTO actualizarProducto(Long idProducto, ProductoCreateDTO dto) {
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
                
        productoRepository.findByNombreProducto(dto.getNombreProducto())
            .filter(p -> !p.getIdProducto().equals(idProducto))
            .ifPresent(p -> {
                throw new BadRequestException("Ya existe otro producto con ese nombre");
            });
        
        productoExistente.setNombreProducto(dto.getNombreProducto());
        productoExistente.setDescripcion(dto.getDescripcion());
        productoExistente.setPrecio(dto.getPrecio());
        productoExistente.setCantidad(dto.getCantidad());;

        Producto productoActualizado = productoRepository.save(productoExistente);
        return productoMapper.toDTO(productoActualizado);
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        
        productoRepository.deleteById(idProducto);
    }

    @Override
    public ProductoDTO cambiarEstadoProducto(Long idProducto, EstadoProducto nuevoEstadoProducto) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
            
        producto.setEstadoProducto(nuevoEstadoProducto);

        return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorEstado(EstadoProducto estadoProducto) {
        return productoRepository.findByEstadoProducto(estadoProducto)
        .stream()
        .map(productoMapper::toDTO)
        .toList();    
    }

    
}
