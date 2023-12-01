package com.tonysanginez.clienteApi.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tonysanginez.clienteApi.bo.IClienteBO;
import com.tonysanginez.clienteApi.dto.InputClienteDTO;
import com.tonysanginez.clienteApi.dto.response.ResponseOk;
import com.tonysanginez.clienteApi.exceptions.BOException;
import com.tonysanginez.clienteApi.exceptions.CustomExceptionHandler;
import com.tonysanginez.clienteApi.util.MessagesUtil;

@RestController
@RequestMapping("/v1/clientes")
@CrossOrigin
public class ClienteApi {
	
	@Autowired
	private IClienteBO objIClienteBO;
	
	@GetMapping
	public ResponseEntity<?> health() {
		return new ResponseEntity<>("Health OK", HttpStatus.OK);
	}
	
	@PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearCliente(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestBody InputClienteDTO objClienteDTO) {
        try {
        	objIClienteBO.crearCliente(objClienteDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.CREATED);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	@PutMapping(value = "/{codigoCliente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarCliente(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoCliente") Long codigoCliente,
            @RequestBody InputClienteDTO objClienteDTO) {
        try {
        	objIClienteBO.editarCliente(codigoCliente, objClienteDTO);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @DeleteMapping(value = "/{codigoCliente}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarCliente(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @PathVariable("codigoCliente") Long codigoCliente) {
        try {
        	objIClienteBO.eliminarCliente(codigoCliente);
            return new ResponseEntity<>(new ResponseOk(MessagesUtil.getMensaje("ts.response.ok", MessagesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            e.printStackTrace();
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    

}
