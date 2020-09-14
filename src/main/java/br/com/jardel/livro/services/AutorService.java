package br.com.jardel.livro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jardel.livro.models.Autor;
import br.com.jardel.livro.models.Livro;
import br.com.jardel.livro.repositorys.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;
	
	public Iterable<Autor> lista() {
		Iterable<Autor> autores = autorRepository.findAll();
		return autores;
	}
	
	public Iterable<Autor> listaAutorLivro(Livro livro) {
		Iterable<Autor> autores = autorRepository.findByLivro(livro);
		System.out.println(autores);
		return autores;
	}
	
	public Autor autorId(long id) {
		return autorRepository.findById(id);
	}
	
	public Autor gravar(Autor autor) {
		return autorRepository.save(autor);
	}
	
	public void excluir(Autor autor) {
		autorRepository.delete(autor);
	}
	
}
