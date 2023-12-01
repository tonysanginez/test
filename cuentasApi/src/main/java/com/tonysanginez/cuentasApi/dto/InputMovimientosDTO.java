package com.tonysanginez.cuentasApi.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InputMovimientosDTO {
	
	    private Long codigoMovimiento;
	    private Date fecha;
	    private String tipoMovimiento;
	    private Double valor;
	    private Double saldo;
	    private Long codigoCuenta;
}
