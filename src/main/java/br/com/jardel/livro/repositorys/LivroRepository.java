package br.com.jardel.livro.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jardel.livro.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	Livro findById(long id);
}
