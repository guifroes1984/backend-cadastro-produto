package br.com.guifroes1984.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.dto.ProdutoRequest;
import br.com.guifroes1984.productbackend.dto.ProdutoResponse;
import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public CategoriaService categoriaService;
	
	public Produto getById(long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
		
		return produto;
	}
	
	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}
	
	public ProdutoResponse salvar(ProdutoRequest produtoRequest) {
		Produto novoProduto = produtoRepository.save(produtoRequest.toEntity());
		return novoProduto.toDTO();
	}
	
	public void deleteById(long id) {
		Produto produto = getById(id);
		produtoRepository.delete(produto);
	}
	
	public void atualizar(long id, Produto produtoAtualizado) {
		Produto produto = getById(id);
		
		if (produtoAtualizado.getCategoria() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não pode ser vazia");
		}
		
		Categoria categoria = categoriaService.getById(produtoAtualizado.getCategoria().getId());
		
		produto.setDescricao(produtoAtualizado.getDescricao());
		produto.setNome(produtoAtualizado.getNome());
		produto.setPreco(produtoAtualizado.getPreco());
		produto.setNovoProduto(produtoAtualizado.isNovoProduto());
		produto.setPromocao(produtoAtualizado.isPromocao());
		produto.setCategoria(categoria);
		
		produtoRepository.save(produto);
	}

}
