package br.com.jardel.livro.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jardel.livro.models.Autor;
import br.com.jardel.livro.models.Livro;
import br.com.jardel.livro.services.AutorService;
import br.com.jardel.livro.services.LivroService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/")
public class LivroController {

	@Autowired
	private LivroService livroService;
	
	@Autowired
	private AutorService autorService;
	
	@GetMapping("/livros")
	public Iterable<Livro> listaLivro(Model model) {
		Iterable<Livro> livros = livroService.lista();
		model.addAttribute("livros", livros);
		
		return livros;
	}
	
	@GetMapping("/livros/{id}")
	public ResponseEntity<Livro> livroId(@PathVariable(value="id") Long livroId) {
		Livro livro = livroService.livroId(livroId);
		
		autorService.listaAutorLivro(livro);
		
		return ResponseEntity.ok().body(livro);
	}
	
	@PostMapping("/livros")
	public void salvarLivro(@RequestBody Livro livro) {
		livroService.gravar(livro);
	}
	
	@PutMapping("/livros/{id}")
	public ResponseEntity<Livro> atualizarLivro(@PathVariable(value="id") Long livroId, 
			@Validated @RequestBody Livro livroDetalhe) {
		Livro livro = livroService.livroId(livroId);
		
		livro.setTitulo(livroDetalhe.getTitulo());
		livro.setDescricao(livroDetalhe.getDescricao());
		livro.setValor(livroDetalhe.getValor());
		final Livro alteraLivro = livroService.gravar(livro);
		return ResponseEntity.ok(alteraLivro);
	}
	
	@DeleteMapping("/livros/{id}")
	public Map<String, Boolean> excluirLivro(@PathVariable(value="id") Long livroId) {
		Livro livro = livroService.livroId(livroId);
		
		livroService.excluir(livro);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("excluido", Boolean.TRUE);
		return response;
	}
	
	@PostMapping("/livros/{id}")
	public void salvarAutor(@PathVariable(value="id") Long livroId,
			@RequestBody Autor autor){
		Livro livro = livroService.livroId(livroId);
		autor.setLivro(livro);
		autorService.gravar(autor);
	}
}
