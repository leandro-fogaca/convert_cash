package com.convertcash.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.convertcash.domain.User;
import com.convertcash.dto.UserDTO;
import com.convertcash.exception.StandardError;
import com.convertcash.repository.UserRepository;
import com.convertcash.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private final UserService userService;
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder encoder;

	public UserController(UserService userService, UserRepository userRepository, PasswordEncoder encoder) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<StandardError> insert(@Valid @RequestBody UserDTO objDto, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new StandardError(errors));
		}
		User obj = userService.fromDTO(objDto);
		obj.setPassword(encoder.encode(obj.getPassword()));
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<StandardError> update(@Valid @RequestBody UserDTO objDto, @PathVariable String id,
			BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new StandardError(errors));
		}
		User obj = userService.fromDTO(objDto);
		obj.setPassword(encoder.encode(obj.getPassword()));
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/validPassword")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String email, @RequestParam String password) {

		Optional<User> optUser = userRepository.findByEmail(email);
		if (optUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		User user = optUser.get();
		boolean valid = encoder.matches(password, user.getPassword());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);

	}

}
