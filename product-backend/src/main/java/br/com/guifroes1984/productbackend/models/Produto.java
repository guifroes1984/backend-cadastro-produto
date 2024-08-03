package br.com.guifroes1984.productbackend.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUTO")
public class Produto {

	@Id
	private int id;
	private String nome;
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;
	
	private boolean promocao;
	private boolean novoProduto;

	private double preco;

	public Produto(int id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto(int id, String nome, String descricao, Categoria categoria, boolean promocao, boolean novoProduto,
			double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.promocao = promocao;
		this.novoProduto = novoProduto;
		this.preco = preco;
	}

	public Produto() {

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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

}
