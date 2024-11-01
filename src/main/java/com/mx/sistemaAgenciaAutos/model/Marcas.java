package com.mx.sistemaAgenciaAutos.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
CREATE TABLE MARCA (
   ID_MARCA NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
   NOMBRE VARCHAR2(80) NOT NULL,
   ORIGEN VARCHAR2(80) NOT NULL,
   ESLOGAN VARCHAR2(150)
);
*/

@Entity
@Table(name="MARCA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Marcas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMarca;
	private String nombre;
	private String origen;
	private String eslogan;
	
	//Cardinalidad
	//1 marca tiene muchos modelos
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	@JsonIgnore //Sirve para omitir una propiedad o lista de propiedades en mi Json
	List<Modelos> lista = new ArrayList<Modelos>();//Esta variable no se agrega en los demás solo en la tabla fuerte
	
}
