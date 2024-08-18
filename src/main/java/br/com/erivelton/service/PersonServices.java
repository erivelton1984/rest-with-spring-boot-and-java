package br.com.erivelton.service;

import br.com.erivelton.data.vo.v1.PersonVO;
import br.com.erivelton.exceptions.ResourceNotFoundException;
import br.com.erivelton.mapper.DozerMapper;
import br.com.erivelton.model.Person;
import br.com.erivelton.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonVO.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll(){

        logger.info("Finding all people!");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){

        logger.info("Find one person");
        var entity =  personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){

        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var entityVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return entityVO;
    }

    public PersonVO update(PersonVO person){

        logger.info("Updating one person");
        var entity = personRepository.findById(person.getId())
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var entityVO = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return entityVO;
    }

    public void delete(Long id){

        logger.info("Deleting one person");
        var entity = personRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No records found this id!"));
        personRepository.delete(entity);
    }
}
