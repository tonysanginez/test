package com.tonysanginez.clienteApi.bo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.tonysanginez.clienteApi.bo.IClienteBO;
import com.tonysanginez.clienteApi.dao.ClienteDAO;
import com.tonysanginez.clienteApi.dao.PersonaDAO;
import com.tonysanginez.clienteApi.dto.InputClienteDTO;
import com.tonysanginez.clienteApi.exceptions.BOException;
import com.tonysanginez.clienteApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.clienteApi.exceptions.RestClientException;
import com.tonysanginez.clienteApi.model.Cliente;
import com.tonysanginez.clienteApi.model.Persona;
import com.tonysanginez.clienteApi.util.GenericUtil;


@Service
public class ClienteBOImpl implements IClienteBO{
	
	@Autowired
	private ClienteDAO objClienteDAO;
	
	@Autowired
	private PersonaDAO objPersonaDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearCliente(InputClienteDTO objClienteDTO) throws BOException {
		GenericUtil.validarCampoRequeridoBO(objClienteDTO.getClave() != null ?
				 objClienteDTO.getClave().toString() : null, "ts.campos.clave");
	    GenericUtil.validarCampoRequeridoBO(objClienteDTO.getEstado()!= null ?
	                objClienteDTO.getEstado().toString() : null, "ts.campos.estado");
	    GenericUtil.validarCampoRequeridoBO(objClienteDTO.getNombres(), "ts.campos.nombres");
	    GenericUtil.validarCampoRequeridoBO(objClienteDTO.getGenero(), "ts.campos.genero");
	    GenericUtil.validarCampoRequeridoBO(objClienteDTO.getEdad(), "ts.campos.edad");
	    GenericUtil.validarCampoRequeridoBO(objClienteDTO.getIdentificacion(), "ts.campos.identificacion");
	    
	    Persona objPersona = new Persona();
	    objPersona.setNombres(objClienteDTO.getNombres());
	    objPersona.setGenero(objClienteDTO.getGenero());
	    objPersona.setEdad(objClienteDTO.getEdad());
	    objPersona.setIdentificacion(objClienteDTO.getIdentificacion());
	    objPersona.setDireccion(objClienteDTO.getDireccion());
	    objPersona.setTelefono(objClienteDTO.getTelefono());
	    
	    objPersonaDAO.persist(objPersona);
	    
	    Cliente objCliente = new Cliente();
	    objCliente.setEstado(objClienteDTO.getEstado());
	    objCliente.setClave(objClienteDTO.getClave());
	    objCliente.setPersona(objPersona);
	    objClienteDAO.persist(objCliente);
	    
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarCliente(Long codigoCliente, InputClienteDTO objClienteDTO) throws BOException {
		Optional<Cliente> optCliente = objClienteDAO.find(codigoCliente);
        if (optCliente.isPresent()) {
        	Cliente objCliente = optCliente.get();
            if (!ObjectUtils.isEmpty(objClienteDTO.getNombres()))
            	objCliente.getPersona().setNombres(objClienteDTO.getNombres());
            if (!ObjectUtils.isEmpty(objClienteDTO.getGenero()))
            	objCliente.getPersona().setGenero(objClienteDTO.getGenero());
            if (!ObjectUtils.isEmpty(objClienteDTO.getEdad())) 
            	objCliente.getPersona().setEdad(objClienteDTO.getEdad());
            if (!ObjectUtils.isEmpty(objClienteDTO.getIdentificacion())) 
            	objCliente.getPersona().setIdentificacion(objClienteDTO.getIdentificacion());
            if (!ObjectUtils.isEmpty(objClienteDTO.getDireccion())) 
            	objCliente.getPersona().setDireccion(objClienteDTO.getDireccion());
            if (!ObjectUtils.isEmpty(objClienteDTO.getTelefono())) 
            	objCliente.getPersona().setTelefono(objClienteDTO.getTelefono());
            if (!ObjectUtils.isEmpty(objClienteDTO.getClave()))
            	objCliente.setClave(objClienteDTO.getClave());
            if (!ObjectUtils.isEmpty(objClienteDTO.getEstado()))
            	objCliente.setEstado(objClienteDTO.getEstado());
            
            objClienteDAO.update(objCliente);
            
        }else {
            throw new BOException("ts.warn.clienteNoExiste");
        }
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarCliente(Long codigoCliente) throws BOException {
	    Optional<Cliente> optCliente = objClienteDAO.find(codigoCliente);
	    
        if (optCliente.isPresent()) {
        	Optional<Persona> optPersona = objPersonaDAO.find(optCliente.get().getPersona().getCodigoPersona());
        	objClienteDAO.remove(optCliente.get());
        	if (optPersona.isPresent()) {
        		objPersonaDAO.remove(optPersona.get());
			}else {
				throw new BOException("ts.warn.personaNoExiste");
			}
        }else {
            throw new BOException("ts.warn.clienteNoExiste");
        }
	}

}
