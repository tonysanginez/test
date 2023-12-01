package com.tonysanginez.clienteApi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tonysanginez.clienteApi.model.Cliente;
import com.tonysanginez.clienteApi.model.Persona;

public class ClienteTest {
	 	@Test
	    public void testGettersAndSetters() {
	        Cliente cliente = new Cliente();
	        cliente.setPersona(new Persona());
	        cliente.getPersona().setNombres("Jose Lema");
	        cliente.getPersona().setDireccion("Otavalo sn y principal");
	        cliente.getPersona().setTelefono("098254785");
	        cliente.setClave("1234");
	        cliente.setEstado("True");

	        assertEquals("Jose Lema", cliente.getPersona().getNombres());
	        assertEquals("Otavalo sn y principal", cliente.getPersona().getDireccion());
	        assertEquals("098254785", cliente.getPersona().getTelefono());
	        assertEquals("1234", cliente.getClave());
	        assertEquals("True", cliente.getEstado());
	    }
}
