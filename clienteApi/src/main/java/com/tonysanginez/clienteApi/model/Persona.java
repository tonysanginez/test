package com.tonysanginez.clienteApi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persona")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_persona")
	private Long codigoPersona;

	@NotNull
	@Size(max = 200)
	@Column(name = "nombres")
	private String nombres;

	@NotNull
	@Size(max = 1)
	@Column(name = "genero")
	private String genero;

	@NotNull
	@Column(name = "edad")
	private Integer edad;

	@NotNull
	@Size(max = 20)
	@Column(name = "identificacion")
	private String identificacion;

	@Size(max = 255)
	@Column(name = "direccion")
	private String direccion;

	@Size(max = 20)
	@Column(name = "telefono")
	private String telefono;

}
