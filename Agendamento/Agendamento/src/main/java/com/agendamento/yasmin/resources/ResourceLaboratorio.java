package com.agendamento.yasmin.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.yasmin.models.Laboratorio;
import com.agendamento.yasmin.services.ServiceLaboratorio;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/laboratorios")
@SecurityRequirement(name = "security_auth")
public class ResourceLaboratorio {

	@Autowired
	private ServiceLaboratorio serviceLaboratorio;
	
	@GetMapping("")
	public ResponseEntity<List<Laboratorio>> listAll(){
		return new ResponseEntity<List<Laboratorio>>(serviceLaboratorio.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/codigo")
	public ResponseEntity<Laboratorio> getOne(@PathVariable("codigo") int id){
		Optional<Laboratorio> laboratorioOptional = serviceLaboratorio.getOne(id);
		
		if (laboratorioOptional.isPresent()) {
			return new ResponseEntity<Laboratorio>(laboratorioOptional.get(),  HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Laboratorio> salvar(@RequestBody Laboratorio laboratorio){
		
		serviceLaboratorio.salvar(laboratorio);
		return new ResponseEntity<Laboratorio>(laboratorio, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/excluir/(codigo)")
	public ResponseEntity<Laboratorio> excluir(@PathVariable("codigo") int codigo){
		
		Optional<Laboratorio>  optionalLaboratorio = serviceLaboratorio.getOne(codigo);
		
		if(optionalLaboratorio.isPresent()) {
			serviceLaboratorio.excluir(optionalLaboratorio.get());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/editar/(codigo)")
	public ResponseEntity<Laboratorio> editar(@PathVariable("codigo")int codigo, @RequestBody Laboratorio laboratorio){
		Optional<Laboratorio>  optionalLaboratorio = serviceLaboratorio.getOne(codigo);
		
		if (optionalLaboratorio.isPresent()) {
			laboratorio.setId(codigo);
			serviceLaboratorio.salvar(laboratorio);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
