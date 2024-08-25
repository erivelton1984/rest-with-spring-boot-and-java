package br.com.erivelton.service;

import br.com.erivelton.controller.PersonController;
import br.com.erivelton.data.vo.v1.PersonVO;
import br.com.erivelton.data.vo.v2.PersonVO2;
import br.com.erivelton.exceptions.ResourceNotFoundException;
import br.com.erivelton.mapper.DozerMapper;
import br.com.erivelton.model.Person;
import br.com.erivelton.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonVO.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll(){

        logger.info("Finding all people!");
        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream().forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return persons;
    }

    public PersonVO findById(Long id) throws Exception {

        logger.info("Find one person");
        var entity =  personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        var entityVO = DozerMapper.parseObject(entity, PersonVO.class);
        entityVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return entityVO;
    }

    public PersonVO create(PersonVO person) throws Exception {

        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var entityVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());
        return entityVO;
    }

    public PersonVO2 create(PersonVO2 person) throws Exception {

        logger.info("Creating one person with V2");
        var entity = DozerMapper.parseObject(person, Person.class);
        var entityVO2 = DozerMapper.parseObject(personRepository.save(entity), PersonVO2.class);
        //entityVO2.add(linkTo(methodOn(PersonController.class).findById(entityVO2.getKey())).withSelfRel());
        return entityVO2;
    }

    public PersonVO update(PersonVO person) throws Exception {

        logger.info("Updating one person");
        var entity = personRepository.findById(person.getKey())
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var entityVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());
        return entityVO;
    }

    public void delete(Long id){

        logger.info("Deleting one person");
        var entity = personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        personRepository.delete(entity);
    }
}