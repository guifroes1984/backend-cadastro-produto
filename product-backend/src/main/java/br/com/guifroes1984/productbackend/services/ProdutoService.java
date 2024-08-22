package br.com.guifroes1984.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.guifroes1984.productbackend.dto.ProdutoRequest;
import br.com.guifroes1984.productbackend.dto.ProdutoResponse;
import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository produtoRepository;

	public ProdutoResponse getById(long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

		return produto.toDTO();
	}

	public List<ProdutoResponse> getAll() {
		return produtoRepository.findAll().stream().map(p -> p.toDTO()).collect(Collectors.toList());
	}

	public ProdutoResponse salvar(ProdutoRequest produtoRequest) {
		try {
			Produto novoProduto = produtoRepository.save(produtoRequest.toEntity());
			return novoProduto.toDTO();
		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException("Categoria não encontrado");
		}

	}

	public void deleteById(long id) {
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Produto não encontrado");
		}
	}

	public void atualizar(long id, ProdutoRequest produtoAtualizado) {

		try {
			Produto produto = produtoRepository.getReferenceById(id);

			Categoria categoria = new Categoria(produtoAtualizado.getCategoria().getId());

			produto.setDescricao(produtoAtualizado.getDescricao());
			produto.setNome(produtoAtualizado.getNome());
			produto.setPreco(produtoAtualizado.getPreco());
			produto.setNovoProduto(produtoAtualizado.isNovoProduto());
			produto.setPromocao(produtoAtualizado.isPromocao());
			produto.setCategoria(categoria);

			produtoRepository.save(produto);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Produto não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new EntityNotFoundException("Categoria não encontrado");
		}
	}

}
