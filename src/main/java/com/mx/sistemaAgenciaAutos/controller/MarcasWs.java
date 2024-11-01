package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.service.MarcasImp;

;

@RestController
@RequestMapping("MarcasWs")
@CrossOrigin
public class MarcasWs {

	@Autowired
	MarcasImp marcasImp;

	// URL: http://localhost:9000/MarcasWs/listar
	@GetMapping("listar")
	public List<Marcas> listar() {
		return marcasImp.listar();
	}

	// URL: http://localhost:9000/MarcasWs/guardar
	/*
	 * @PostMapping("guardar") public ResponseEntity<?> guardar(@RequestBody Marcas
	 * marca) { boolean existe = marcasImp.guardar(marca);
	 * 
	 * if (existe) { return new
	 * ResponseEntity<>("Error: La marca con el mismo id o nombre ya existe.",
	 * HttpStatus.BAD_REQUEST); } else { return new ResponseEntity<>(marca,
	 * HttpStatus.CREATED); } }
	 */
	
	// URL: http://localhost:9000/MarcasWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marcas) {
		String response = marcasImp.guardar(marcas);
		if (response.equals("El id ya Existe")) {
			return new ResponseEntity<>("Ese ID ya existe", HttpStatus.OK);
		} else if (response.equals("El nombre ya Existe")) {
			return new ResponseEntity<>("El nombre ya Existe", HttpStatus.OK);
		} else
			return new ResponseEntity<>(marcas, HttpStatus.CREATED);
	}

	// URL: http://localhost:9000/MarcasWs/buscar
	@PostMapping("buscar")
	public ResponseEntity<?> buscar(@RequestBody Marcas marcas) {

		Marcas marca = marcasImp.buscarPorId(marcas.getIdMarca());

		if (marca == null)
			return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
	}

	// URL: http://localhost:9000/MarcasWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marcas) {
		boolean respuesta = marcasImp.editar(marcas);
		if (!respuesta) {
			return new ResponseEntity<>("Error: La marca con el ID especificado no existe.", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(marcas, HttpStatus.CREATED);
		}
	}

	// URL: http://localhost:9000/MarcasWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Marcas marcas) {
		boolean respuesta = marcasImp.eliminar(marcas.getIdMarca());
		if (!respuesta) {
			return new ResponseEntity<>("Error: La marca con el ID especificado no existe.", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>("Marca eliminada exitosamente.", HttpStatus.OK);
		}
	}
}
