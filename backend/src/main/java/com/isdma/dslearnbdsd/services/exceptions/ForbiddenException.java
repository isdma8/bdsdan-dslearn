package com.isdma.dslearnbdsd.services.exceptions;

public class ForbiddenException extends RuntimeException {  //usada para retornarmos erro 403 (token do user é valido mas porque algum motivo de regra de negocio ele nao pode aceder ao recurso)
	private static final long serialVersionUID = 1L;
	
	public ForbiddenException(String msg) {
		super(msg);
	}

}
