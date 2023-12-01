package com.tonysanginez.cuentasApi.dto;

import java.math.BigDecimal;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDTO {

	private Date fecha;
	private String nombres;
	private String numeroCuenta;
	private String tipoCuenta;
	private Double saldoInicial;
	private String estado;
	private Double valor;
	private Double saldo;
}
