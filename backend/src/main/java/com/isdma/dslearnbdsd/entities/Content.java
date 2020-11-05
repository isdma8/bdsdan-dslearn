package com.isdma.dslearnbdsd.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_content")   //tabela de herança nao precisa de mais nada, no fundo é so atributo adicional
public class Content extends Lesson{
	private static final long serialVersionUID = 1L;
	
	private String textContent;
	private String videoUri;

	public Content() {
		
	}

	public Content(Long id, String title, Integer position, Section section, String textContent, String videoUri) {
		super(id, title, position, section); //reaproveita construtor do Lesson
		this.textContent = textContent;
		this.videoUri = videoUri;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getVideoUri() {
		return videoUri;
	}

	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}
	
	//Nao precisa equals & hashcode porque estamos a herdar do lesson isso
	
	
}
