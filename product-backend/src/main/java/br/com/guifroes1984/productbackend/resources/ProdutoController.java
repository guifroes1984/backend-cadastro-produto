package br.com.guifroes1984.productbackend.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import br.com.guifroes1984.productbackend.dto.ProdutoRequest;
import br.com.guifroes1984.productbackend.dto.ProdutoResponse;
import br.com.guifroes1984.productbackend.services.ProdutoService;

@RestController
@CrossOrigin
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	public ProdutoService produtoService;
	
	@PostMapping
	public ResponseEntity<ProdutoResponse> salvar(@Validated @RequestBody ProdutoRequest produtoRequest) {
		
		ProdutoResponse produtoResponse = produtoService.salvar(produtoRequest);
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produtoResponse.getId())
				.toUri();
		
		return ResponseEntity.created(local).body(produtoResponse);
	}

	@GetMapping("{id}")
	public ResponseEntity<ProdutoResponse> getProduto(@PathVariable long id) {
		ProdutoResponse produto = produtoService.getDTOById(id);
		return ResponseEntity.ok(produto);
	}

	@GetMapping
	public ResponseEntity<List<ProdutoResponse>> getProdutos() {
		return ResponseEntity.ok(produtoService.getAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> removeProduto(@PathVariable long id) {
		produtoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizarProduto(@PathVariable long id, @RequestBody ProdutoRequest produtoAtualizado) {
		produtoService.atualizar(id, produtoAtualizado);
		return ResponseEntity.ok().build();
	}

}
