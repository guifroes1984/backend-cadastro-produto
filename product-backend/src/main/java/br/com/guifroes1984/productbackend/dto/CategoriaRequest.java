package br.com.guifroes1984.productbackend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.guifroes1984.productbackend.models.Categoria;

public class CategoriaRequest {

	@NotBlank(message = "O nome não pode ser em branco")
	@Size(min = 3, max = 255, message = "Comprimento do nome min=3 e max=255")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria toEntity() {
		return new Categoria(nome);
	}

}
