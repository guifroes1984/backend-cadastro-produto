package br.com.guifroes1984.productbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.guifroes1984.productbackend.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
