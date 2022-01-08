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
import br.com.foxi.controleveiculosapi.repository.CostumerRepository;
import br.com.foxi.controleveiculosapi.service.exceptions.DataIntegrityException;
import br.com.foxi.controleveiculosapi.service.exceptions.ObjectNotFoundException;

@Service
public class CostumerService {
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private AdressesService adressesService;

    public Costumer find(Integer id) {
        Optional<Costumer> obj = costumerRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Costumer.class.getName()));
    }

    public Page<Costumer> findAll(Integer page,Integer linesPage,String direction,String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, linesPage,Direction.valueOf(direction),orderBy);
        return costumerRepository.findAll(pageRequest);
    }

    public Costumer save(Costumer obj) {
        Costumer costumer = costumerRepository.findByDocument(obj.getDocument());
        if (costumer != null) 
            throw new DataIntegrityException("Documento ja consta na base de dados");
        
        Adresses adresses = adressesService.find(obj.getAdresses().getId());


        costumer = costumerRepository.save(obj);
        costumer.getAdresses().setCity(adresses.getCity());
        costumer.getAdresses().setComplement(adresses.getComplement());
        costumer.getAdresses().setStreet(adresses.getStreet());
        costumer.getAdresses().setDistrict(adresses.getDistrict());
        costumer.getAdresses().setZipCode(adresses.getZipCode());
        return costumer;

    }
    public void delete(@PathVariable Integer id){

        find(id);
        try {
            costumerRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Não é possivel excluir a categoria pois possui produtos vinculados");
        }
    }
    public Costumer update(Costumer obj){
        Costumer costumer = costumerRepository.findByDocument(obj.getDocument());
        if (costumer.getId() != obj.getId()) 
            throw new DataIntegrityException("Documento pertence a outro cadastro");

        return costumerRepository.save(obj);
    }
}
