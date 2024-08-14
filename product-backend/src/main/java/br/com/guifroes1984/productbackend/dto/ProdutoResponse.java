package br.com.guifroes1984.productbackend.dto;

public class ProdutoResponse {

	private long id;
	private String nome;
	private String descricao;
	private boolean promocao;
	private boolean novoProduto;
	private double preco;
	private CategoriaResponse categoria;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public CategoriaResponse getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaResponse categoria) {
		this.categoria = categoria;
	}

}
