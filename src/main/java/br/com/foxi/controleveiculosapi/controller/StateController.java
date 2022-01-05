package br.com.foxi.controleveiculosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.foxi.controleveiculosapi.domain.State;
import br.com.foxi.controleveiculosapi.service.StateService;

@RestController
@RequestMapping(value = "states")
public class StateController {
	@Autowired
	private StateService service;

	@PostMapping
	public void postUser(@RequestBody State state) {
		service.createUser(state);
	}

    @PreAuthorize("hasRole('ROLE_STATES')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<State>> findall() {

		Page<State> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
