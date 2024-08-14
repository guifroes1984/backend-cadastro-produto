package br.com.guifroes1984.productbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.dto.CategoriaRequest;
import br.com.guifroes1984.productbackend.dto.CategoriaResponse;
import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Categoria getById(int id) {
		Categoria Categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
		
		return Categoria;
	}
	
	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}
	
	public CategoriaResponse salvar(CategoriaRequest categoriaRequest) {
		Categoria categoria = categoriaRepository.save(categoriaRequest.toEntity());
		return categoria.toDTO();
	}
	
	public void deleteById(int id) {
		Categoria Categoria = getById(id);
		categoriaRepository.delete(Categoria);
	}
	
	public void atualizar(int id, Categoria categoriaAtualizado) {
		Categoria Categoria = getById(id);

		Categoria.setNome(categoriaAtualizado.getNome());
		
		categoriaRepository.save(Categoria);
	}

}
