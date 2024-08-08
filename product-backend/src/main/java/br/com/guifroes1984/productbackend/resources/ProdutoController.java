package br.com.guifroes1984.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;
import br.com.guifroes1984.productbackend.repositories.ProdutoRepository;
import br.com.guifroes1984.productbackend.services.ProdutoService;

@RestController
@CrossOrigin
public class ProdutoController {
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	@Autowired
	public ProdutoService produtoService;
	
	@PostMapping("produtos")
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		
		produto = produtoRepository.save(produto);
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		
		return ResponseEntity.created(local).body(produto);
	}

	@GetMapping("produtos/{id}")
	public ResponseEntity<Produto> getProduto(@PathVariable int id) {
		Produto produto = produtoService.getById(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping("produtos")
	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}
	
	@DeleteMapping("produtos/{id}")
	public ResponseEntity<Void> removeProduto(@PathVariable int id) {
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
		
		produtoRepository.delete(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("produtos/{id}")
	public ResponseEntity<Void> atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
		
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
		
		if (produtoAtualizado.getCategoria() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não pode ser vazia");
		}
		
		Categoria categoria = categoriaRepository.findById(produtoAtualizado.getCategoria().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado."));
		
		produto.setDescricao(produtoAtualizado.getDescricao());
		produto.setNome(produtoAtualizado.getNome());
		produto.setPreco(produtoAtualizado.getPreco());
		produto.setNovoProduto(produtoAtualizado.isNovoProduto());
		produto.setPromocao(produtoAtualizado.isPromocao());
		produto.setCategoria(categoria);
		
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}

}
