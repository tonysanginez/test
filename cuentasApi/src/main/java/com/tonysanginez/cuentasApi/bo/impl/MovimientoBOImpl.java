package com.tonysanginez.cuentasApi.bo.impl;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.tonysanginez.cuentasApi.bo.IMovimientosBO;
import com.tonysanginez.cuentasApi.dao.CuentaDAO;
import com.tonysanginez.cuentasApi.dao.MovimientosDAO;
import com.tonysanginez.cuentasApi.dto.InputMovimientosDTO;
import com.tonysanginez.cuentasApi.exceptions.BOException;
import com.tonysanginez.cuentasApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.cuentasApi.exceptions.RestClientException;
import com.tonysanginez.cuentasApi.model.Cuenta;
import com.tonysanginez.cuentasApi.model.Movimientos;
import com.tonysanginez.cuentasApi.util.GenericUtil;
@Service
public class MovimientoBOImpl implements IMovimientosBO{

	@Autowired
	private MovimientosDAO objMovimientosDAO;
	
	@Autowired
	private CuentaDAO objCuentaDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearMovimiento(InputMovimientosDTO objMovimientoDTO) throws BOException {
		//GenericUtil.validarCampoRequeridoBO(objMovimientoDTO.getFecha() != null ?
			//	 objMovimientoDTO.getFecha().toString() : null, "ts.campos.fecha");
	    GenericUtil.validarCampoRequeridoBO(objMovimientoDTO.getTipoMovimiento()!= null ?
	                objMovimientoDTO.getTipoMovimiento().toString() : null, "ts.campos.tipoMovimiento");
	    GenericUtil.validarCampoRequeridoBO(objMovimientoDTO.getValor(), "ts.campos.valor");
	    GenericUtil.validarCampoRequeridoBO(objMovimientoDTO.getCodigoCuenta(), "ts.campos.cuenta");
	    
	    Optional<Cuenta> cuentaOpt = objCuentaDAO.find(objMovimientoDTO.getCodigoCuenta());
	    if (!cuentaOpt.isPresent()) {
	        throw new BOException("Cuenta no encontrada.");
	    }
	    Cuenta cuenta = cuentaOpt.get();
	    
	    Double nuevoSaldo = cuenta.getSaldoInicial()+objMovimientoDTO.getValor();
	    if (nuevoSaldo<0) {
	        throw new BOException("Saldo no disponible");
	    }
	    cuenta.setSaldoInicial(nuevoSaldo);
	    
	    
	    
	    Movimientos objMovimientos = new Movimientos();
	    objMovimientos.setFecha(new Date());
	    objMovimientos.setTipoMovimiento(objMovimientoDTO.getTipoMovimiento());
	    objMovimientos.setValor(objMovimientoDTO.getValor());
	    objMovimientos.setSaldo(nuevoSaldo);
	    objMovimientos.setCodigoCuenta(objMovimientoDTO.getCodigoCuenta());
	    objMovimientosDAO.persist(objMovimientos);
	    objCuentaDAO.update(cuenta);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarMovimiento(Long codigoMovimiento, InputMovimientosDTO objMovimientoDTO) throws BOException {
		Optional<Movimientos> optMovimiento = objMovimientosDAO.find(codigoMovimiento);
        if (optMovimiento.isPresent()) {
        	Movimientos objMovimientos = optMovimiento.get();
            //if (!ObjectUtils.isEmpty(objMovimientoDTO.getFecha()))
            	//objMovimientos.setFecha(objMovimientoDTO.getFecha());
            if (!ObjectUtils.isEmpty(objMovimientoDTO.getTipoMovimiento()))
            	objMovimientos.setTipoMovimiento(objMovimientoDTO.getTipoMovimiento());
            if (!ObjectUtils.isEmpty(objMovimientoDTO.getValor())) 
            	objMovimientos.setValor(objMovimientoDTO.getValor());
            if (!ObjectUtils.isEmpty(objMovimientoDTO.getSaldo()))
            	objMovimientos.setSaldo(objMovimientoDTO.getSaldo());
            if (!ObjectUtils.isEmpty(objMovimientoDTO.getCodigoCuenta()))
            	objMovimientos.setCodigoCuenta(objMovimientoDTO.getCodigoCuenta());
            
            objMovimientosDAO.update(objMovimientos);
            
        }else {
            throw new BOException("ts.warn.movimientoNoExiste");
        }
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarMovimiento(Long codigoMovimiento) throws BOException {
		Optional<Movimientos> optMovimiento = objMovimientosDAO.find(codigoMovimiento);
        if (optMovimiento.isPresent()) {
        	objMovimientosDAO.remove(optMovimiento.get());
        }else {
            throw new BOException("ts.warn.movimientoNoExiste");
        }
	}

	@Override
	public Map<String, Object> obtenerEstadoCuenta(Long codigoCliente, Date fechaInicio, Date fechaFin) throws BOException{
		GenericUtil.validarCampoRequeridoBO(codigoCliente.toString(), "ts.campos.codigoCliente");
		GenericUtil.validarCampoRequeridoBO(fechaInicio.toString(), "ts.campos.fechaInicio");
		GenericUtil.validarCampoRequeridoBO(fechaFin.toString(), "ts.campos.fechaFin");
      return objMovimientosDAO.obtenerEstadoCuenta(codigoCliente,fechaInicio,fechaFin);
	}

}
