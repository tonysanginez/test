package com.tonysanginez.clienteApi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tonysanginez.clienteApi.dto.InputClienteDTO;
import com.tonysanginez.clienteApi.dto.response.ResponseOk;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteApiIntegrationTest {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "http://localhost:" + port + "/v1/clientes";
    }
    /**
     * 
    
    @Test
    public void testCreateCliente() {
    	   InputClienteDTO cliente = new InputClienteDTO();
    	    cliente.setNombres("Tony Sanginez");
    	    cliente.setGenero("Masculino");

    	    ResponseEntity<ResponseOk> postResponse = restTemplate.postForEntity(getRootUrl(), cliente, ResponseOk.class);
    	    Assertions.assertNotNull(postResponse);
    	    Assertions.assertNotNull(postResponse.getBody());
    	    Assertions.assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
    } */
}
