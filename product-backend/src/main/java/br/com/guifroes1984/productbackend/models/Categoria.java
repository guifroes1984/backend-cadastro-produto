package br.com.guifroes1984.productbackend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CATEGORIA")
public class Categoria {

	@Id
	private int id;
	
	private String nome;

	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Categoria() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
