package br.com.erivelton.mapper.custom;

import br.com.erivelton.data.vo.v2.PersonVO2;
import br.com.erivelton.model.Person;

import java.util.Date;

public class PersonMapper {

    public PersonVO2 convertEntityToVo(Person person){
        PersonVO2 vo = new PersonVO2();
        vo.setId(person.getId());
        vo.setAddress(person.getAddress());
        vo.setBirthDate(new Date());
        vo.setFirstName(person.getFirstName());
        vo.setGender(person.getGender());
        return vo;
    }

    public Person convertVoToEntity(PersonVO2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setAddress(person.getAddress());
        //entity.setBirthDate(new Date());
        entity.setFirstName(person.getFirstName());
        entity.setGender(person.getGender());
        return entity;
    }

}
