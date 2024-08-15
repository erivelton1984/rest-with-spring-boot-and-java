package br.com.erivelton.service;

import br.com.erivelton.model.Person;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(Person.class.getName());

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;


    }

    public Person findById(String id){

        logger.info("Find one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Erivelton Ribeiro");
        person.setLastName("Pinto");
        person.setAddress("Rua Coronel Carlos Bardelli, 367 - Curitiba - Paraná - Brasil");
        person.setGender("Male");

        return person;
    }

    public Person create(Person person){

        logger.info("Creating one person");

        return person;
    }

    public Person update(Person person){

        logger.info("Updating one person");

        return person;
    }

    public void delete(String id){

        logger.info("Deleting one person");
    }


    private Person mockPerson(int i) {

        logger.info("Find all people" + i);

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Name: " + i);
        person.setLastName("Last name: " + i);
        person.setAddress("Address: " + i);
        person.setGender("Male");

        return person;

    }



}
