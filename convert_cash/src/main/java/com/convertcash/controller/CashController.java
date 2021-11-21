package com.convertcash.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.convertcash.domain.Cash;
import com.convertcash.dto.CashDTO;
import com.convertcash.services.CashService;

@RestController
@RequestMapping(value="/currencies")
public class CashController {

	@Autowired
	private CashService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<CashDTO>> findAll() {
		List<Cash> list = service.findAll();
		List<CashDTO> listDto = list.stream().map(x -> new CashDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<CashDTO> findById(@PathVariable int id) {
		Cash obj = service.findById(id);
		return ResponseEntity.ok().body(new CashDTO(obj));
	}

	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody CashDTO objDto) {
		Cash obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable int id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody CashDTO objDto, @PathVariable int id) {
		Cash obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
}
