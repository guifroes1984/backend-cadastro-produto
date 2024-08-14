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

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.services.CategoriaService;

@RestController
@CrossOrigin
@RequestMapping("categorias")
public class CategoriaController {
	
	@Autowired
	public CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Validated @RequestBody Categoria categoria) {
		
		categoria = categoriaService.salvar(categoria);
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity.created(local).body(categoria);
	}

	@GetMapping("{id}")
	public ResponseEntity<Categoria> getCategorias(@PathVariable int id) {
		Categoria categoria = categoriaService.getById(id);
		return ResponseEntity.ok(categoria);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> getCategorias() {
		return ResponseEntity.ok(categoriaService.getAll());
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> removeCategoria(@PathVariable int id) {
		categoriaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Void> atualizarCategoria(@PathVariable int id, @RequestBody Categoria categoriaAtualizado) {
		categoriaService.atualizar(id, categoriaAtualizado);
		return ResponseEntity.ok().build();
	}

}
