package br.com.guifroes1984.productbackend.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.models.Produto;

public class ProdutoRequest {

	@NotBlank(message = "O nome não pode ser em branco")
	@Size(min = 3, max = 255, message = "Comprimento do nome min=3 e max=255")
	private String nome;

	@NotBlank(message = "Descrição não pode ser em branco")
	@Size(min = 3, max = 1024, message = "Comprimento do descrição min=3 e max=1024")
	private String descricao;

	private boolean promocao;

	private boolean novoProduto;

	@Min(value = 0, message = "Preço min valor = 0")
	private double preco;

	@NotNull
	@Valid
	private IntegerDTO categoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPromocao() {
		return promocao;
	}

	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}

	public boolean isNovoProduto() {
		return novoProduto;
	}

	public void setNovoProduto(boolean novoProduto) {
		this.novoProduto = novoProduto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public IntegerDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(IntegerDTO categoria) {
		this.categoria = categoria;
	}

	public Produto toEntity() {
		
		Produto produto = new Produto();
		
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setNovoProduto(novoProduto);
		produto.setPromocao(promocao);
		produto.setPreco(preco);
		produto.setCategoria(new Categoria(categoria.getId()));
		
		return produto;
	}

}
