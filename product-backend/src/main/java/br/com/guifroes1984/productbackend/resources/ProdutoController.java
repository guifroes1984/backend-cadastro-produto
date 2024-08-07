package br.com.guifroes1984.productbackend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.guifroes1984.productbackend.models.Produto;
import br.com.guifroes1984.productbackend.repositories.ProdutoRepository;

@RestController
@CrossOrigin
public class ProdutoController {

	private List<Produto> produtos = new ArrayList<>();
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
	@PostMapping("produtos")
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		produto.setId(produtos.size() + 1);
		produtos.add(produto);
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(produto.getId())
				.toUri();
		
		return ResponseEntity.created(local).body(produto);
	}

	@GetMapping("produtos/{id}")
	public ResponseEntity<Produto> getProduto(@PathVariable int id) {
		Produto prod = produtos.stream()
								.filter(p -> p.getId() == id)
								.findFirst()
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
		
		return ResponseEntity.ok(prod);
	}

	@GetMapping("produtos")
	public List<Produto> getProdutos() {
		return produtoRepository.findAll();
	}

}
