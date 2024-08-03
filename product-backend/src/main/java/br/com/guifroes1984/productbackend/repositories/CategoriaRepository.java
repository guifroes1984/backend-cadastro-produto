package br.com.guifroes1984.productbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.guifroes1984.productbackend.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
