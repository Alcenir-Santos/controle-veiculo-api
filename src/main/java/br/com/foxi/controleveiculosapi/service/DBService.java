package br.com.foxi.controleveiculosapi.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.foxi.controleveiculosapi.domain.User;
import br.com.foxi.controleveiculosapi.repository.UserRepository;

@Service
public class DBService {
	@Autowired
	private BCryptPasswordEncoder cpe;
	@Autowired
	private UserRepository userRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		List<String> roles = new ArrayList<>();
		roles.add("USERS");
		roles.add("MANAGERS");
		User user = new User();
		user.setEmail("teste@teste.com");
		user.setName("root");
		user.setUsername("root");
		user.setPassword(cpe.encode("root"));
		user.setRoles(roles);
		userRepository.save(user);
	}
	
}
