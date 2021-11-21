package com.convertcash.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convertcash.domain.Cash;
import com.convertcash.dto.CashDTO;
import com.convertcash.repository.CashRepository;
import com.convertcash.exception.ObjectNotFoundException;

@Service
public class CashService {

	@Autowired
	private CashRepository repository;

	public List<Cash> findAll() {
		return repository.findAll();
	}

	public Cash findById(int id) {
		Optional<Cash> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public Cash insert(Cash obj) {
		return repository.insert(obj);
	}

	public void delete(int id) {
		findById(id);
		repository.deleteById(id);
	}

	public Cash update(Cash obj) {
		Cash newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Cash newObj, Cash obj) {
		newObj.setName(obj.getName());
		newObj.setQuantity(obj.getQuantity());
	}

	public Cash fromDTO(CashDTO objDto) {
		return new Cash(objDto.getId(), objDto.getName(), objDto.getQuantity());
	}
}
