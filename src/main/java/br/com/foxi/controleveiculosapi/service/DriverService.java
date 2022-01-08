package br.com.foxi.controleveiculosapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.foxi.controleveiculosapi.domain.Adresses;
import br.com.foxi.controleveiculosapi.domain.Costumer;
import br.com.foxi.controleveiculosapi.domain.Driver;
import br.com.foxi.controleveiculosapi.repository.DriverRepository;
import br.com.foxi.controleveiculosapi.service.exceptions.DataIntegrityException;
import br.com.foxi.controleveiculosapi.service.exceptions.ObjectNotFoundException;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private AdressesService adressesService;

    public Driver find(Integer id) {
        Optional<Driver> obj = driverRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Costumer.class.getName()));
    }

    public Page<Driver> findAll(Integer page, Integer linesPage, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
        return driverRepository.findAll(pageRequest);
    }

    public Driver save(Driver obj) {
        Driver driver = driverRepository.findByDocument(obj.getDocument());
        if (driver != null)
            throw new DataIntegrityException("Documento ja consta na base de dados");

        Adresses adresses = adressesService.find(obj.getAdresses().getId());

        driver = driverRepository.save(obj);
        driver.getAdresses().setCity(adresses.getCity());
        driver.getAdresses().setComplement(adresses.getComplement());
        driver.getAdresses().setStreet(adresses.getStreet());
        driver.getAdresses().setDistrict(adresses.getDistrict());
        driver.getAdresses().setZipCode(adresses.getZipCode());
        return driver;
    }
  
    public Driver update(Driver obj){
        Driver driver = driverRepository.findByDocument(obj.getDocument());
        if (driver.getId() != obj.getId()) 
            throw new DataIntegrityException("Documento pertence a outro cadastro");

        return driverRepository.save(obj);
    }
    public void delete(@PathVariable Integer id){

        find(id);
        try {
            driverRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possivel excluir o Motorista pois tem cargas vinculadas");
        }
    }
}
