package com.tonysanginez.clienteApi.bo;

import com.tonysanginez.clienteApi.dto.InputClienteDTO;
import com.tonysanginez.clienteApi.exceptions.BOException;

public interface IClienteBO {

	public void crearCliente(InputClienteDTO objClienteDTO) throws BOException;
	public void editarCliente(Long codigoCliente, InputClienteDTO objClienteDTO) throws BOException;
	public void eliminarCliente(Long codigoCliente) throws BOException;
}
