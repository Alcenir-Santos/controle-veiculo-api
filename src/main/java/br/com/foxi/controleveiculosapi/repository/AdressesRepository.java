package br.com.foxi.controleveiculosapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.foxi.controleveiculosapi.domain.Adresses;
@Repository
public interface AdressesRepository extends JpaRepository<Adresses,Integer> {
    
    @Transactional(readOnly = true)
    Adresses findByZipCode(String zipCode);
    
}
