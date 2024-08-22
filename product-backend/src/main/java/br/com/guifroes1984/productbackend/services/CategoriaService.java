package br.com.guifroes1984.productbackend.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.guifroes1984.productbackend.dto.CategoriaRequest;
import br.com.guifroes1984.productbackend.dto.CategoriaResponse;
import br.com.guifroes1984.productbackend.models.Categoria;
import br.com.guifroes1984.productbackend.repositories.CategoriaRepository;
import br.com.guifroes1984.productbackend.services.exceptions.DatabaseException;

@Service
public class CategoriaService {
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public CategoriaResponse getDTOById(int id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
		
		return categoria.toDTO();
	}
	
	public Categoria getById(int id) {
		Categoria Categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
		
		return Categoria;
	}
	
	public List<CategoriaResponse> getAll() {
		return categoriaRepository.findAll()
									.stream()
									.map(c -> c.toDTO())
									.collect(Collectors.toList());
	}
	
	public CategoriaResponse salvar(CategoriaRequest categoriaRequest) {
		Categoria categoria = categoriaRepository.save(categoriaRequest.toEntity());
		return categoria.toDTO();
	}
	
	public void deleteById(int id) {
		try {
			categoriaRepository.deleteById(id);
		} 
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Restringir violação, categoria não pode ser excluída");
		}
		catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Categoria não encontrada");
		}
	}
	
	public void atualizar(int id, CategoriaRequest categoriaAtualizado) {
		Categoria Categoria = getById(id);
		Categoria.setNome(categoriaAtualizado.getNome());
		categoriaRepository.save(Categoria);
	}

}
