package com.demo.productos.gestor_productos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.productos.gestor_productos.entities.EstadoProducto;
import com.demo.productos.gestor_productos.entities.Producto;
import java.util.List;


@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long>{
    Optional<Producto> findByNombreProducto(String nombreProducto);
    Optional<Producto> findByIdProducto(Long idProducto);
    List<Producto> findByEstadoProducto(EstadoProducto estadoProducto);
}
