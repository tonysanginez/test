package com.tonysanginez.cuentasApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputCuentaDTO {
	 private Long codigoCuenta;
	 private String numeroCuenta;
	 private String tipoCuenta;
	 private Double saldoInicial;
	 private String estado;
	 private Long codigoCliente;
}
