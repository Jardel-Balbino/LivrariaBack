package br.com.jardel.livro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jardel.livro.models.Autor;
import br.com.jardel.livro.services.AutorService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/")
public class AutorController {

	@Autowired
	private AutorService autorService;
	
	@GetMapping("/autores")
	public Iterable<Autor> listaAutor(Model model) {
		Iterable<Autor> autores = autorService.lista();
		model.addAttribute("autores", autores);
		
		return autores;
	}
	
}
