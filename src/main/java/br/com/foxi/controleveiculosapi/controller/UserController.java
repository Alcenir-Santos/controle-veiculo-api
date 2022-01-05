package br.com.foxi.controleveiculosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.foxi.controleveiculosapi.domain.User;
import br.com.foxi.controleveiculosapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping
	public void postUser(@RequestBody User user) {
		service.createUser(user);
	}

	@GetMapping("/search")
	public Page<User> search(@RequestParam("searchTerm") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return service.search(searchTerm, page, size);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<User>> findall() {

		Page<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
