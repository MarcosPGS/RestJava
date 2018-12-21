package com.unidesc.localiza.resource;

import java.util.List;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Semestre;
import com.unidesc.localiza.negocio.service.SemestreService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class SemestreResource {
	
	@Autowired
	SemestreService semestreService;
	
	
	
	@GetMapping("/semestre")
	public List<Semestre> buscaSemesttre(){
		return semestreService.buscarTodosSemestres();
	}
	
	@GetMapping("/semestre/semestre/{semestre}")
	public ResponseEntity<Semestre> buscaSemestre(@PathVariable String semestre){
		Semestre semestreEncontrado = semestreService.buscarPorSemestre(semestre);
		if(semestreEncontrado == null) {
			return ok(null);
		}
		return ok(semestreEncontrado);
	}
	
	@PostMapping("/semestre")
	public Semestre salvaSemestre(@RequestBody Semestre semestre) {
		return semestreService.salvarSemestre(semestre);
	}
	
	@PutMapping("/semestre")
	public Semestre atualizaSemestre(@RequestBody Semestre semestre) {
		return semestreService.atualizarSemestre(semestre);
	}
	
	@DeleteMapping("/semestre")
	public void deletaSemestre(@RequestBody Semestre semestre) {
		semestreService.deletarSemestre(semestre);
	}

}
