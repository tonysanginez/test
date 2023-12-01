package com.tonysanginez.cuentasApi.bo;

import com.tonysanginez.cuentasApi.dto.InputCuentaDTO;
import com.tonysanginez.cuentasApi.exceptions.BOException;

public interface ICuentaBO {

	public void crearCuenta(InputCuentaDTO objCuentaDTO) throws BOException;
	public void editarCuenta(Long codigoCuenta, InputCuentaDTO objCuentaDTO) throws BOException;
	public void eliminarCuenta(Long codigoCuenta) throws BOException;
}
