package br.com.guifroes1984.productbackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;

@RestController
@CrossOrigin
public class CategoriaController {
	
//	private List<Categoria> categorias = Arrays.asList(new Categoria(1, "Produção Própria"),
//													   new Categoria(2, "Nacional"), 
//													   new Categoria(3, "Importado"), 
//													   new Categoria(4, "Premium")
//	);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
//	@GetMapping("categorias/{id}")
//	public ResponseEntity<Categoria> getCategoria(@PathVariable int id) {
//		Categoria cat = categorias.stream()
//								.filter(p -> p.getId() == id)
//								.findFirst()
//								.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrado."));
//		
//		return ResponseEntity.ok(cat);
//	}

	@GetMapping("categorias")
	public List<Categoria> getCategoria() {
		return categoriaRepository.findAll();
	}

}
