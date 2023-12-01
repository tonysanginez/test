package com.tonysanginez.cuentasApi.bo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.tonysanginez.cuentasApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.cuentasApi.exceptions.RestClientException;
import com.tonysanginez.cuentasApi.bo.ICuentaBO;
import com.tonysanginez.cuentasApi.dao.CuentaDAO;
import com.tonysanginez.cuentasApi.dto.InputCuentaDTO;
import com.tonysanginez.cuentasApi.exceptions.BOException;
import com.tonysanginez.cuentasApi.model.Cuenta;
import com.tonysanginez.cuentasApi.util.GenericUtil;

@Service
public class CuentaBOImpl implements ICuentaBO{

	@Autowired
	private CuentaDAO objCuentaDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearCuenta(InputCuentaDTO objCuentaDTO) throws BOException {
		GenericUtil.validarCampoRequeridoBO(objCuentaDTO.getNumeroCuenta() != null ?
				 objCuentaDTO.getNumeroCuenta().toString() : null, "ts.campos.numeroCuenta");
	    GenericUtil.validarCampoRequeridoBO(objCuentaDTO.getEstado()!= null ?
	                objCuentaDTO.getEstado().toString() : null, "ts.campos.estado");
	    GenericUtil.validarCampoRequeridoBO(objCuentaDTO.getTipoCuenta(), "ts.campos.tipoCuenta");
	    GenericUtil.validarCampoRequeridoBO(objCuentaDTO.getSaldoInicial(), "ts.campos.saldoInicial");
	    GenericUtil.validarCampoRequeridoBO(objCuentaDTO.getCodigoCliente(), "ts.campos.cliente");
	    
	    Cuenta objCuenta = new Cuenta();
	    objCuenta.setNumeroCuenta(objCuentaDTO.getNumeroCuenta());
	    objCuenta.setTipoCuenta(objCuentaDTO.getTipoCuenta());
	    objCuenta.setSaldoInicial(objCuentaDTO.getSaldoInicial());
	    objCuenta.setEstado(objCuentaDTO.getEstado());
	    objCuenta.setCodigoCliente(objCuentaDTO.getCodigoCliente());
	    objCuentaDAO.persist(objCuenta);
	    
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarCuenta(Long codigoCuenta, InputCuentaDTO objCuentaDTO) throws BOException {
		Optional<Cuenta> optCuenta = objCuentaDAO.find(codigoCuenta);
        if (optCuenta.isPresent()) {
        	Cuenta objCuenta = optCuenta.get();
            if (!ObjectUtils.isEmpty(objCuentaDTO.getNumeroCuenta()))
            	objCuenta.setNumeroCuenta(objCuentaDTO.getNumeroCuenta());
            if (!ObjectUtils.isEmpty(objCuentaDTO.getTipoCuenta()))
            	objCuenta.setTipoCuenta(objCuentaDTO.getTipoCuenta());
            if (!ObjectUtils.isEmpty(objCuentaDTO.getSaldoInicial())) 
            	objCuenta.setSaldoInicial(objCuentaDTO.getSaldoInicial());
            if (!ObjectUtils.isEmpty(objCuentaDTO.getEstado()))
            	objCuenta.setEstado(objCuentaDTO.getEstado());
            
            objCuentaDAO.update(objCuenta);
            
        }else {
            throw new BOException("ts.warn.cuentaNoExiste");
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarCuenta(Long codigoCuenta) throws BOException {
		Optional<Cuenta> optCuenta = objCuentaDAO.find(codigoCuenta);
        if (optCuenta.isPresent()) {
        	objCuentaDAO.remove(optCuenta.get());
        }else {
            throw new BOException("ts.warn.cuentaNoExiste");
        }
	}

}
