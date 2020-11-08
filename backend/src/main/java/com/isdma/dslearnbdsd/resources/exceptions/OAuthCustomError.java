package com.isdma.dslearnbdsd.resources.exceptions;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthCustomError implements Serializable {
	private static final long serialVersionUID = 1L;

	private String error;
	
	@JsonProperty       //aqui no java tudo camel case so na hora de serializar o json é que convermtemos para snakecase para cumprir com o que ta agora a sair nos campos de erro antes de personalizarmos
	private String errorDescription;
	
	public OAuthCustomError() {
		
	}

	public OAuthCustomError(String error, String errorDescription) {
		super();
		this.error = error;
		this.errorDescription = errorDescription;
	}
	
	
	
}
