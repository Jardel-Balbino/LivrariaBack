package br.com.jardel.livro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jardel.livro.models.Livro;
import br.com.jardel.livro.repositorys.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
	public Iterable<Livro> lista() {
		Iterable<Livro> livros = livroRepository.findAll();
		return livros;
	}
	
	public Livro livroId(long id) {
		return livroRepository.findById(id);
	}
	
	public Livro gravar(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public String excluir(Livro livro) {
		String resultado;
		try {
			livroRepository.delete(livro);
			resultado = "Livro excluido com sucesso!";
		} catch (Exception e) {
			resultado = "Livro não excluido!";
		}
		return resultado;
	}
	
}
