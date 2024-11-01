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

import com.mx.sistemaAgenciaAutos.model.Modelos;
import com.mx.sistemaAgenciaAutos.service.ModelosImp;

@RestController
@RequestMapping("ModelosWs")
@CrossOrigin
public class ModelosWs {
	@Autowired
	ModelosImp modelosImp;

	// URL: http://localhost:9000/ModelosWs/listar
	@GetMapping("listar")
	public List<Modelos> listar() {
		return modelosImp.listar();
	}

	// URL: http://localhost:9000/ModelosWs/guardar
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelos) {
		String response = modelosImp.guardar(modelos);
		if (response.equals("El ID de la marca no existe")) {
			return new ResponseEntity<>("Ese ID de marca no existe", HttpStatus.OK);
		} else if (response.equals("El id ya Existe")) {
			return new ResponseEntity<>("El id del modelo ya Existe", HttpStatus.OK);
		} else if (response.equals("El Nombre ya Existe")) {
			return new ResponseEntity<>("El Nombre del modelo ya Existe", HttpStatus.OK);
		} else
			return new ResponseEntity<>(modelos, HttpStatus.CREATED);
	}

	// URL: http://localhost:9000/ModelosWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelos) {
		String response = modelosImp.editar(modelos);
		if (response.equals("El ID de la marca no existe")) {
			return new ResponseEntity<>("Ese ID de marca no existe", HttpStatus.OK);
		} else if (response.equals("El ID del modelo no existe")) {
			return new ResponseEntity<>("El id del modelo NO Existe", HttpStatus.OK);
		} else
			return new ResponseEntity<>(modelos, HttpStatus.CREATED);
	}

	// URL: http://localhost:9000/ModelosWs/buscar
	@PostMapping("buscar")
	public ResponseEntity<?> buscar(@RequestBody Modelos modelos) {

		Modelos models = modelosImp.buscarPorId(modelos.getIdModelo());

		return new ResponseEntity<>(models, HttpStatus.OK);
	}
	
	//URL: http://localhost:9000/ModelosWs/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Modelos modelos) {
		boolean response = modelosImp.eliminar(modelos.getIdModelo());
		if(!response) 
			return new ResponseEntity<>("Error: el modelo con el ID especificado no existe.", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("Modelo eliminada exitosamente.", HttpStatus.OK);
	}

}
