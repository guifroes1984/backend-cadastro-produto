package br.com.guifroes1984.productbackend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.models.Categoria;

@RestController
@CrossOrigin
public class CategoriaController {
	
	private List<Categoria> categorias = Arrays.asList(new Categoria(1, "Produção Própria"),
													   new Categoria(2, "Nacional"), 
													   new Categoria(3, "Importado"), 
													   new Categoria(4, "Premium")
	);
	
	@GetMapping("categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable int id) {
		Categoria cat = categorias.stream()
								.filter(p -> p.getId() == id)
								.findFirst()
								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado."));
		
		return ResponseEntity.ok(cat);
	}

	@GetMapping("categorias")
	public List<Categoria> getCategoria() {
		return categorias;
	}

}
