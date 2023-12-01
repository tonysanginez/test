package com.tonysanginez.cuentasApi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tonysanginez.cuentasApi.bo.ICuentaBO;
import com.tonysanginez.cuentasApi.dto.InputCuentaDTO;
import com.tonysanginez.cuentasApi.dto.response.ResponseOk;
import com.tonysanginez.cuentasApi.exceptions.BOException;
import com.tonysanginez.cuentasApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.cuentasApi.util.MessagesUtil;

@RestController
@RequestMapping("/v1/cuentas")
@CrossOrigin
public class CuentaApi {
	
	@Autowired
	private ICuentaBO objICuentaBO;
	
	@PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearCuenta(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestBody InputCuentaDTO objCuentaDTO) {
        try {
        	objICuentaBO.crearCuenta(objCuentaDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	@PutMapping(value = "/{codigoCuenta}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarCuenta(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoCuenta") Long codigoCuenta,
            @RequestBody InputCuentaDTO objCuentaDTO) {
        try {
        	objICuentaBO.editarCuenta(codigoCuenta, objCuentaDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @DeleteMapping(value = "/{codigoCuenta}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarCuenta(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoCuenta") Long codigoCuenta) {
        try {
        	objICuentaBO.eliminarCuenta(codigoCuenta);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
}
