package com.examen.abm.controller;

import com.examen.abm.enums.TipoDocumento;
import com.examen.abm.model.Person;
import com.examen.abm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/")
    public Object getAll() {
        return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public Object findById(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/find/")
    public Object findPerson(@RequestParam(required = false)String name,@RequestParam(required = false) TipoDocumento docType) {
        return new ResponseEntity<>(service.findPersona(name,docType),HttpStatus.OK);
    }

    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object createPerson(@RequestBody Person person){

        String response = service.create(person);
        if(response!=null){
            return new ResponseEntity<>("Persona creada correctamente",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Error al crear persona", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object editarPersona(@RequestBody Person person){

        String response = service.update(person);
        if(response!=null){
            return new ResponseEntity<>("Persona editada correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al editar persona", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public Object deletePerson(@PathVariable(value = "id") Long id){

        String response = service.deleteById(id);
        if(response!=null){
            return new ResponseEntity<>("Persona eliminada correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al eliminar persona", HttpStatus.BAD_REQUEST);
        }
    }


}
