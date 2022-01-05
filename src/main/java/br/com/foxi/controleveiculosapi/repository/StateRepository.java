package br.com.foxi.controleveiculosapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.foxi.controleveiculosapi.domain.State;
import br.com.foxi.controleveiculosapi.domain.User;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

	User findByName(String name);

	@Query("FROM State c " + "WHERE LOWER(c.name) like %:searchTerm% " + "OR LOWER(c.abbreviation) like %:searchTerm%")
	Page<State> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
