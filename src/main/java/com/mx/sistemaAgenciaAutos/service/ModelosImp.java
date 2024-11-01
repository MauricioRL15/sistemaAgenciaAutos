package com.mx.sistemaAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.dao.ModelosDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.model.Modelos;

@Service
public class ModelosImp {

	@Autowired
	ModelosDao modelosDao;
	@Autowired
	MarcasDao marcasDao;

	@Transactional(readOnly = true)
	public List<Modelos> listar() {
		return modelosDao.findAll();
	}

	// Validar idModelo, nombre no exista y el idMarca
	@Transactional
	public String guardar(Modelos modelos) {

		String respuesta = "";
		boolean existeModelo = false;
		boolean existeMarca = false;

		// Primero validar lo de las tablas foraneas
		for (Marcas m : marcasDao.findAll()) {
			// validar que exista el id de la marca:
			if (m.getIdMarca().equals(modelos.getMarca().getIdMarca())) {
				existeMarca = true;
				for (Modelos mo : modelosDao.findAll()) {
					if (mo.getIdModelo().equals(modelos.getIdModelo())) {
						existeModelo = true;
						respuesta = "El id ya Existe";
						break;
					} else if (mo.getNombre().equals(modelos.getNombre())) {
						existeModelo = true;
						respuesta = "El Nombre ya Existe";
						break;
					}
				}
				break;
			}
		}
		if (!existeMarca) {
			respuesta = "El ID de la marca no existe";
			existeModelo = true;
		} else if (!existeModelo) {
			modelosDao.save(modelos);
			respuesta = "Se guardo correctamente";
		}
		return respuesta;
	}

	// Editar validar que el id modelo y el id marca existan
	@Transactional
	public String editar(Modelos modelos) {
		String respuesta = "";
		boolean banderaModelo = false;
		boolean banderaMarca = false;

		for (Marcas m : marcasDao.findAll()) {
			if (m.getIdMarca().equals(modelos.getMarca().getIdMarca())) {
				banderaMarca = true;
				for (Modelos mo : modelosDao.findAll()) {
					if (mo.getIdModelo().equals(modelos.getIdModelo())) {
						banderaModelo = true;
						modelosDao.save(modelos);
						respuesta = "Se actualizo correctamente";
					}
				}
				break;
			}
		}
		if (!banderaMarca) {
			respuesta = "El ID de la marca no existe";
		} else if (!banderaModelo) {
			respuesta = "El ID del modelo no existe";
		}
		return respuesta;
	}

	// Buscar por ID
	@Transactional(readOnly = true)
	public Modelos buscarPorId(Long id) {
		return modelosDao.findById(id).orElse(null);
	}

	// Elimiar checando que el id de modelo exista:
	@Transactional
	public boolean eliminar(Long id) {
		boolean existe = false;
		for (Modelos m : modelosDao.findAll()) {
			if (m.getIdModelo().equals(id)) {
				modelosDao.deleteById(id);
				existe = true;
				break;
			}
		}
		return existe;
	}

}
