package br.com.jardel.livro.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jardel.livro.models.Autor;
import br.com.jardel.livro.models.Livro;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	Autor findById(long id);
	
	Iterable<Autor> findByLivro(Livro livro);
}
