package com.isdma.dslearnbdsd.services.exceptions;

public class UnauthorizedException extends RuntimeException { //erro 401 (token nao é valido do user)
	private static final long serialVersionUID = 1L;
	
	public UnauthorizedException(String msg) {
		super(msg);
	}

}
