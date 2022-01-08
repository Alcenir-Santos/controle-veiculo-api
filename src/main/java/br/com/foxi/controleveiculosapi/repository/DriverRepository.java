package br.com.foxi.controleveiculosapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.foxi.controleveiculosapi.domain.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    
    @Transactional(readOnly = true)
    Driver findByDocument(String doc);
}
