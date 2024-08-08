package br.com.guifroes1984.productbackend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.services.CategoriaService;

@RestController
@CrossOrigin
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping("categorias/{id}")
	public ResponseEntity<Categoria> getCategoria(@PathVariable int id) {
		Categoria categoria = categoriaService.getById(id);
		return ResponseEntity.ok(categoria);
	}

	@GetMapping("categorias")
	public List<Categoria> getCategoria() {
		return categoriaService.getAll();
	}

}
