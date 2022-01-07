package br.com.foxi.controleveiculosapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.foxi.controleveiculosapi.domain.Costumer;
import br.com.foxi.controleveiculosapi.service.CostumerService;

@RestController
@RequestMapping("/costumers")
public class CostumerController {
    @Autowired
    private CostumerService costumerService;

    @PreAuthorize("hasRole('ROLE_COSTUMER')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page<Costumer>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "10") Integer linesPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "fullName") String orderBy) {

        Page<Costumer> list = costumerService.findAll(page,linesPage,direction,orderBy);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ROLE_COSTUMER')")
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Costumer> findById(@PathVariable Integer id) {
        Costumer list = costumerService.find(id);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ROLE_COSTUMER')")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Costumer obj) {
        Costumer costumer = costumerService.save(obj);

        return ResponseEntity.ok().body(costumer);
    }
    @PreAuthorize("hasRole('ROLE_COSTUMER')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        costumerService.delete(id);

        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasRole('ROLE_COSTUMER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody Costumer obj, @PathVariable Integer id){
        obj.setId(id);
        Costumer objNew = costumerService.update(obj);

        return ResponseEntity.ok().body(objNew);
    }
}
