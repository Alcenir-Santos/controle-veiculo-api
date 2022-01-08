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

import br.com.foxi.controleveiculosapi.domain.Driver;
import br.com.foxi.controleveiculosapi.service.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Page<Driver>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "10") Integer linesPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "fullName") String orderBy) {

        Page<Driver> list = driverService.findAll(page, linesPage, direction, orderBy);
        return ResponseEntity.ok().body(list);
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Driver obj) {
        Driver costumer = driverService.save(obj);

        return ResponseEntity.ok().body(costumer);
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        driverService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@Valid @RequestBody Driver obj, @PathVariable Integer id) {
        obj.setId(id);
        Driver objNew = driverService.update(obj);

        return ResponseEntity.ok().body(objNew);
    }

    @PreAuthorize("hasRole('ROLE_DRIVER')")
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<Driver> findById(@PathVariable Integer id) {
        Driver list = driverService.find(id);
        return ResponseEntity.ok().body(list);
    }
}
