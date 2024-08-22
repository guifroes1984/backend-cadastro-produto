package br.com.guifroes1984.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.dto.ProdutoRequest;
import br.com.guifroes1984.productbackend.dto.ProdutoResponse;
import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;
import br.com.guifroes1984.productbackend.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Produto getById(long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
		
		return produto;
	}
	
	public List<ProdutoResponse> getAll() {
		return produtoRepository.findAll()
								.stream()
								.map(p -> p.toDTO())
								.collect(Collectors.toList());
	}
	
	public ProdutoResponse salvar(ProdutoRequest produtoRequest) {
		Produto novoProduto = produtoRepository.save(produtoRequest.toEntity());
		return novoProduto.toDTO();
	}
	
	public void deleteById(long id) {
		Produto produto = getById(id);
		produtoRepository.delete(produto);
	}
	
	public void atualizar(long id, ProdutoRequest produtoAtualizado) {
		Produto produto = getById(id);
		
		Categoria categoria = categoriaRepository.findById(produtoAtualizado.getCategoria().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
		
		produto.setDescricao(produtoAtualizado.getDescricao());
		produto.setNome(produtoAtualizado.getNome());
		produto.setPreco(produtoAtualizado.getPreco());
		produto.setNovoProduto(produtoAtualizado.isNovoProduto());
		produto.setPromocao(produtoAtualizado.isPromocao());
		produto.setCategoria(categoria);
		
		produtoRepository.save(produto);
	}

	public ProdutoResponse getDTOById(long id) {
		Produto produto = getById(id);
		return produto.toDTO();
	}

}
