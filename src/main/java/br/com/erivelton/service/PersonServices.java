package br.com.erivelton.service;

import br.com.erivelton.exceptions.ResourceNotFoundException;
import br.com.erivelton.model.Person;
import br.com.erivelton.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(Person.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll(){

        logger.info("Finding all people!");

        return personRepository.findAll();
    }

    public Person findById(Long id){

        logger.info("Find one person");

        return personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
    }

    public Person create(Person person){

        logger.info("Creating one person");

        return personRepository.save(person);
    }

    public Person update(Person person){

        logger.info("Updating one person");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id){

        logger.info("Deleting one person");

        var entity = personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));

        personRepository.delete(entity);

        logger.info("Deleting one person");
    }
}
