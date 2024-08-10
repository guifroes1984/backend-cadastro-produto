package br.com.guifroes1984.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.services.ProdutoService;

@RestController
@CrossOrigin
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	public ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		
		produto = produtoService.salvar(produto);
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		
		return ResponseEntity.created(local).body(produto);
	}

	@GetMapping("{id}")
	public ResponseEntity<Produto> getProduto(@PathVariable int id) {
		Produto produto = produtoService.getById(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> getProdutos() {
		return ResponseEntity.ok(produtoService.getAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> removeProduto(@PathVariable int id) {
		produtoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
		produtoService.atualizar(id, produtoAtualizado);
		return ResponseEntity.ok().build();
	}

}
