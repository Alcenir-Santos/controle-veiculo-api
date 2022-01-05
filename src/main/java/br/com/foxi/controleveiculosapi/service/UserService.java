package br.com.foxi.controleveiculosapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.foxi.controleveiculosapi.domain.User;
import br.com.foxi.controleveiculosapi.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	public void createUser(User user) {
		String pass = user.getPassword();

		// criptografando antes de salvar no banco
		user.setPassword(encoder.encode(pass));
		repository.save(user);
	}

	public Page<User> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");

		return repository.search(searchTerm.toLowerCase(), pageRequest);
	}

	public Page<User> findAll() {
		int page = 0;
		int size = 10;
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
		return new PageImpl<>(repository.findAll(), pageRequest, size);
	}
}
