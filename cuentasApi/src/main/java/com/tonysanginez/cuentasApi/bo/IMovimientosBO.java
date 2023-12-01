package com.tonysanginez.cuentasApi.bo;

import java.util.Date;
import java.util.Map;

import com.tonysanginez.cuentasApi.dto.InputMovimientosDTO;
import com.tonysanginez.cuentasApi.exceptions.BOException;

public interface IMovimientosBO {
	
	public void crearMovimiento(InputMovimientosDTO objMovimientoDTO) throws BOException;
	public void editarMovimiento(Long codigoMovimiento, InputMovimientosDTO objMovimientosDTO) throws BOException;
	public void eliminarMovimiento(Long codigoMovimiento) throws BOException;
	public Map<String, Object> obtenerEstadoCuenta(Long codigoCliente, Date fechaInicio, Date fechaFin) throws BOException;
}
