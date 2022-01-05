package br.com.foxi.controleveiculosapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.foxi.controleveiculosapi.domain.State;
import br.com.foxi.controleveiculosapi.repository.StateRepository;

@Service
public class StateService {
	@Autowired
	private StateRepository repository;

	public void createUser(State state) {
		repository.save(state);
	}

	public Page<State> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

		return repository.search(searchTerm.toLowerCase(), pageRequest);
	}

	public Page<State> findAll() {
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
		return new PageImpl<>(repository.findAll(), pageRequest, size);
	}
}
