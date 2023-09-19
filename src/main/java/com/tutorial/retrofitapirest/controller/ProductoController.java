package com.tutorial.retrofitapirest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.retrofitapirest.model.Producto;
import com.tutorial.retrofitapirest.repository.ProductoRepository;

@RestController
public class ProductoController {
	@Autowired
    ProductoRepository productoRepository;

	@GetMapping("/productos")
	  public ResponseEntity<List<Producto>> getAllTutorials() {
	    try {
	      List<Producto> prods = productoRepository.findAll();
	      if (prods.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(prods, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/productos/{id}")
	  public ResponseEntity<Producto> getTutorialById(@PathVariable("id") long id) {
	    Optional<Producto> tutorialData = productoRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

    @PostMapping("/productos")
    public ResponseEntity<Producto> createTutorial(@RequestBody Producto producto) {
      try {
        Producto _prod = productoRepository.save(new Producto(0, producto.getNombre(), producto.getPrecio()));
        return new ResponseEntity<>(_prod, HttpStatus.CREATED);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateTutorial(@PathVariable("id") long id, @RequestBody Producto producto) {
      Optional<Producto> prodData = productoRepository.findById(id);
      if (prodData.isPresent()) {
        Producto _p = prodData.get();
        _p.setNombre(producto.getNombre());
        _p.setPrecio(producto.getPrecio());
        return new ResponseEntity<>(productoRepository.save(_p), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
      try {
    	  productoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }


}
