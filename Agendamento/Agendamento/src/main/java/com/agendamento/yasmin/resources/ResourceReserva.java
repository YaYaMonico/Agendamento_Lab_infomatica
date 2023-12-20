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
import com.agendamento.yasmin.models.Reserva;
import com.agendamento.yasmin.services.ServiceReserva;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/reservas")
@SecurityRequirement(name = "security_auth")
public class ResourceReserva {
	
	@Autowired
	private ServiceReserva serviceReserva;
	
	@GetMapping("")
	public ResponseEntity<List<Reserva>> listAll(){
		return new ResponseEntity<List<Reserva>>(serviceReserva.listAll(), HttpStatus.OK);
	}
	
	@GetMapping("/codigo")
	public ResponseEntity<Reserva> getOne(@PathVariable("codigo") int id){
		Optional<Reserva> reservaOptional = serviceReserva.getOne(id);
		
		if (reservaOptional.isPresent()) {
			return new ResponseEntity<Reserva>(reservaOptional.get(),  HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Reserva> salvar(@RequestBody Reserva reserva){
		
		serviceReserva.salvar(reserva);
		return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/excluir/(codigo)")
	public ResponseEntity<Reserva> excluir(@PathVariable("codigo") int codigo){
		
		Optional<Reserva>  optionalReserva = serviceReserva.getOne(codigo);
		
		if(optionalReserva.isPresent()) {
			serviceReserva.excluir(optionalReserva.get());
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/editar/(codigo)")
	public ResponseEntity<Reserva> editar(@PathVariable("codigo")int codigo, @RequestBody Reserva reserva){
		Optional<Reserva>  optionalReserva = serviceReserva.getOne(codigo);
		
		if (optionalReserva.isPresent()) {
			reserva.setId(codigo);
			serviceReserva.salvar(reserva);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
