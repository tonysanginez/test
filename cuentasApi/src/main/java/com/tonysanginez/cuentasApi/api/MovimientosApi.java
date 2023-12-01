package com.tonysanginez.cuentasApi.api;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tonysanginez.cuentasApi.bo.IMovimientosBO;
import com.tonysanginez.cuentasApi.dto.InputMovimientosDTO;
import com.tonysanginez.cuentasApi.dto.response.ResponseError;
import com.tonysanginez.cuentasApi.dto.response.ResponseOk;
import com.tonysanginez.cuentasApi.exceptions.BOException;
import com.tonysanginez.cuentasApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.cuentasApi.util.MessagesUtil;

@RestController
@RequestMapping("/v1/movimientos")
@CrossOrigin
public class MovimientosApi {

	@Autowired
	private IMovimientosBO objIMovimientosBO;

	@GetMapping(value = "/reportes")
	public ResponseEntity<?> obtenerEstadoCuenta(
			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,
			@RequestParam(value = "codigoCliente") Long codigoCliente,
			@RequestParam(value = "fechaInicio") Date fechaInicio, @RequestParam(value = "fechaFin") Date fechaFin) {

		try {
			Map<String, Object> data = objIMovimientosBO.obtenerEstadoCuenta(codigoCliente, fechaInicio, fechaFin);
			return new ResponseEntity<>(new ResponseOk(
					MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)), data),
					HttpStatus.OK);
		} catch (BOException e) {

			throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
		}
	}
	
	@PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearMovimiento(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestBody InputMovimientosDTO objMovimientoDTO) {
        try {
        	objIMovimientosBO.crearMovimiento(objMovimientoDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
        	 
             // Aquí puedes manejar el mensaje de error específico
             String errorMessage = e.getTranslatedMessage(strLanguage);
             if (errorMessage.equals("Saldo no disponible")) {
            	 return new ResponseEntity<>(new ResponseError("Saldo Insuficiente", "El saldo es insuficiente para realizar este retiro", 0),
	                     HttpStatus.BAD_REQUEST);
             } else {
            	 e.printStackTrace();
                 throw new CustomExceptionHandler(errorMessage, e.getData());
             }
        }
    }
	@PutMapping(value = "/{codigoMovimiento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarMovimiento(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoMovimiento") Long codigoMovimiento,
            @RequestBody InputMovimientosDTO objMovimientoDTO) {
        try {
        	objIMovimientosBO.editarMovimiento(codigoMovimiento, objMovimientoDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @DeleteMapping(value = "/{codigoMovimiento}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarMovimiento(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoMovimiento") Long codigoMovimiento) {
        try {
        	objIMovimientosBO.eliminarMovimiento(codigoMovimiento);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
   
}
