package com.tutorial.retrofitapirest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tutorial.retrofitapirest.model.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long> {

}

