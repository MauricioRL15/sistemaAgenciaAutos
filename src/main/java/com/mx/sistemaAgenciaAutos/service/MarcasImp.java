package com.mx.sistemaAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;

@Service
public class MarcasImp {

	@Autowired
	MarcasDao marcasDao;

	@Transactional(readOnly = true)
	public List<Marcas> listar() {
		return marcasDao.findAll();
	}

	// Al guardar validar que el id y nombre no se repitan al guardar
	/*@Transactional
	public boolean guardar(Marcas marca) {
	    boolean existe = false;

	    for (Marcas m : marcasDao.findAll()) {
	        if (m.getIdMarca().equals(marca.getIdMarca()) || m.getNombre().equalsIgnoreCase(marca.getNombre())) {
	            existe = true;
	            break;
	        }
	    }

	    if (!existe) {
	        marcasDao.save(marca);
	    }

	    return existe;
	}*/
	
	@Transactional
	public String guardar(Marcas marcas) {
		String respuesta = "";
		boolean existe = false;
		for (Marcas m : marcasDao.findAll()) {
			if (m.getIdMarca().equals(marcas.getIdMarca())){
				existe = true;
				respuesta = "El id ya Existe";
				break;
			} else if (m.getNombre().equals(marcas.getNombre())) {
				existe = true;
				respuesta = "El nombre ya Existe";
				break;
			}
		}
		if (!existe) {
			marcasDao.save(marcas);
			respuesta = "Se guardo correctamente";
		}
		return respuesta;
	}
	
	
	// Buscar por id
	@Transactional(readOnly = true)
	public Marcas buscarPorId(Long id) {
		return marcasDao.findById(id).orElse(null);
	}

	// Editar
	@Transactional
	public boolean editar(Marcas marcas) {
		if (!marcasDao.existsById(marcas.getIdMarca())) {
			return false;
		}
		marcasDao.save(marcas);
		return true;
	}

	// Eliminar
	/*@Transactional
	public boolean eliminar(Long id) {
		if (!marcasDao.existsById(id)) {
			return false;
		}
		marcasDao.deleteById(id);
		return true;
	}*/
	@Transactional
	public boolean eliminar(Long id) {
	    boolean existe = false;

	    for (Marcas m : marcasDao.findAll()) {
	        if (m.getIdMarca().equals(id)) {
	            marcasDao.deleteById(id);
	            existe = true;
	            break;
	        }
	    }

	    return existe;
	}

}
