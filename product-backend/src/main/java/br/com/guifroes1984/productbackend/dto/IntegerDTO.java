package br.com.guifroes1984.productbackend.dto;

import javax.validation.constraints.Min;

public class IntegerDTO {

	@Min(value = 1, message = "Min valor 1")
	private int id;
	
	public IntegerDTO() {
	}

	public IntegerDTO(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
