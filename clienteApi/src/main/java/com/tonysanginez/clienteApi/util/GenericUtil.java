package com.tonysanginez.clienteApi.util;


import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.tonysanginez.clienteApi.exceptions.BOException;


public class GenericUtil {
	
	private GenericUtil() {
		throw new IllegalStateException("Utility class");
	}
	public static void validarCampoRequeridoBO(String strCampoRequerido, String strNombreCampo) 
	throws BOException {
		if (StringUtils.isEmpty(strCampoRequerido)) {
			throw new BOException("ts.warn.campoObligatorio", new Object[] { strNombreCampo });
		}
	}
	public static void validarCampoRequeridoBO(Object objCampoRequerido, String strNombreCampo) 
	throws BOException {
		if (ObjectUtils.isEmpty(objCampoRequerido)) {
			throw new BOException("ts.warn.campoObligatorio", new Object[] { strNombreCampo });
		}
	}
	
}