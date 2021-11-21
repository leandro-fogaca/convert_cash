package com.convertcash.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.convertcash.domain.User;
import com.convertcash.dto.UserDTO;
import com.convertcash.repository.UserRepository;

import ch.qos.logback.core.pattern.parser.Parser;

import com.convertcash.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	public User findByEmail(String email) {
		Optional<User> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ObjectNotFoundException("E-mail não encontrado"));
	}

	public User insert(User obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword(), null);
	}

}