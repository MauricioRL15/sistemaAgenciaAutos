package com.mx.sistemaAgenciaAutos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/*
CREATE TABLE MODELO (
	    ID_MODELO NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	    NOMBRE VARCHAR2(80) NOT NULL,
	    TIPO VARCHAR2(75) NOT NULL,
	    PRECIO NUMBER NOT NULL,
	    FECHA_LANZAMIENTO DATE NOT NULL,
	    ID_MARCA NUMBER,
	    FOREIGN KEY (ID_MARCA) REFERENCES MARCA(ID_MARCA)
	);
*/
@Entity
@Table(name="MODELO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modelos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModelo;
	private String nombre;
	private String tipo;
	private Float precio;
	private Date fechaLanzamiento;
	
	//Cardinalidad
	//Muchos modelos pertenecen a una marca
	//fetch, indicamos como debe ser cargada la entidad
	//FetchType, indicamos que la relacion va a ser cargada al momento.
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="ID_MARCA")
	Marcas marca;
	
	
}
