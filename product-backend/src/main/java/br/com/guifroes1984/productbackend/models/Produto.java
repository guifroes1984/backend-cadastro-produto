package br.com.guifroes1984.productbackend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name = "TBL_PRODUTO")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 255)
	@NotBlank(message = "O nome não pode ser em branco")
	@Size(min = 3, max = 255, message = "Comprimento do nome min=3 e max=255")
	private String nome;
	
	@Column(nullable = false, length = 1024)
	@NotBlank(message = "Descrição não pode ser em branco")
	@Size(min = 3, max = 1024, message = "Comprimento do descrição min=3 e max=1024")
	private String descricao;

	private boolean promocao;
	
	private boolean novoProduto;

	@Min(value = 0, message = "Preço min valor = 0")
	private Double preco;
	
	@ManyToOne
	private Categoria categoria;

	public Produto(Long id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto(Long id, String nome, String descricao, Categoria categoria, boolean promocao, boolean novoProduto,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", promocao=" + promocao
				+ ", novoProduto=" + novoProduto + ", preco=" + preco + "]";
	}

}
