package com.demo.productos.gestor_productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.productos.gestor_productos.DTOs.ProductoCreateDTO;
import com.demo.productos.gestor_productos.DTOs.ProductoDTO;
import com.demo.productos.gestor_productos.entities.EstadoProducto;
import com.demo.productos.gestor_productos.exceptions.ResourceNotFoundException;
import com.demo.productos.gestor_productos.services.ProductoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/productos")
@Validated
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> registrarProducto(@Valid @RequestBody ProductoCreateDTO dto) {
        System.out.println("Recibiendo solicitud POST para registrar producto: " + dto.getNombreProducto());
        ProductoDTO productoNuevo = productoService.registrarProducto(dto);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/buscar/id/{idProducto}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long idProducto) {
        return productoService.buscarPorId(idProducto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<ProductoDTO> buscarPorNombre(@PathVariable String nombre) {
        return productoService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long idProducto,
            @Valid @RequestBody ProductoCreateDTO dto) {
        ProductoDTO actualizado = productoService.actualizarProducto(idProducto, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idProducto}/estado")
    public ResponseEntity<ProductoDTO> cambiarEstado(
            @PathVariable Long idProducto,
            @RequestParam EstadoProducto estado) {
        return ResponseEntity.ok(
                productoService.cambiarEstadoProducto(idProducto, estado));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProductoDTO>> listarPorEstado(@PathVariable EstadoProducto estado) {
        return ResponseEntity.ok(productoService.obtenerProductosPorEstado(estado));
    }

}
