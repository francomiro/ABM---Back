package com.examen.abm.service;

import com.examen.abm.enums.TipoDocumento;
import com.examen.abm.exception.CreatePersonaException;
import com.examen.abm.exception.PersonNotFoundException;
import com.examen.abm.model.Person;
import com.examen.abm.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public String create(Person person) {
        Person newPerson = person;
        try {
            if (newPerson != null) {
                personRepository.saveAndFlush(newPerson);
                return "OK";
            }else{
                throw new CreatePersonaException("Error al crear una persona.");
            }
        } catch (CreatePersonaException e) {
            return null;
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    public List<Person> findAll() {
        List<Person> listaPeople = personRepository.findAll();
        return listaPeople;
    }

    public List<Person> findPersona(String nombreCompleto, TipoDocumento tipoDocumento) {

        if(nombreCompleto == null){
            nombreCompleto="";
        }
        if(tipoDocumento != null){
            return personRepository.findPersonaWithPartOfNameAndTipoDoc(nombreCompleto,tipoDocumento);
        }else{
            return personRepository.findPersonaWithPartOfName(nombreCompleto);
        }
    }

    public String update(Person person) {
        try {
            Person personOp = findById(person.getId());
            if (personOp == null) {
                throw new PersonNotFoundException("Persona no encontrada");
            }
            personOp.setPerApellido(person.getPerApellido());
            personOp.setPerNombre(person.getPerNombre());
            personOp.setPerFechaNacimiento(person.getPerFechaNacimiento());
            personOp.setPerNumeroDocumento(person.getPerNumeroDocumento());
            personOp.setPerTipoDocumento(person.getPerTipoDocumento());

            personRepository.saveAndFlush(personOp);

            return "La persona fue actualizada correctamente";
        } catch (PersonNotFoundException e) {
            return null;
        }

    }

    public Person findById(Long id) {
        try {
            Optional<Person> person = personRepository.findById(id);
            if (!person.isPresent()) {
                throw new PersonNotFoundException("Persona no encontrada");
            }
            return person.get();
        } catch (PersonNotFoundException e) {
            return null;
        }
    }

    public String deleteById(Long id) {

        try {
            Person person = findById(id);
            if (person !=null){
                personRepository.deleteById(id);
                return "Eliminada exitosamente";
            }else{
                throw new PersonNotFoundException("Persona no encontrada");
            }
        } catch (PersonNotFoundException e) {
            return null;
        }


    }
}
