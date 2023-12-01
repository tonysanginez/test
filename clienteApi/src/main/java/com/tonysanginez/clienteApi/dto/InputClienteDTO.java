package com.tonysanginez.clienteApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputClienteDTO {
	 private Long codigoCliente;
	 private String clave;
	 private String nombres;
	 private String genero;
	 private Integer edad;
	 private String identificacion;
	 private String direccion;
	 private String telefono;
	 private String estado;
}
