package br.com.guifroes1984.productbackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;

@RestController
@CrossOrigin
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable int id) {
		Categoria categoria = categoriaRepository.findById(id)
											.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado."));
		
		return ResponseEntity.ok(categoria);
	}

	@GetMapping("categorias")
	public List<Categoria> getCategoria() {
		return categoriaRepository.findAll();
	}

}
