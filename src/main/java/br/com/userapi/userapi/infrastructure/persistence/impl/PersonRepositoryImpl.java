package br.com.userapi.userapi.infrastructure.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;
import br.com.userapi.userapi.infrastructure.entity.PersonEntity;
import br.com.userapi.userapi.infrastructure.mapper.PersonMapperRepo;
import br.com.userapi.userapi.infrastructure.persistence.jpa.SpringDataPersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final SpringDataPersonRepository dataPersonRepository;

    public PersonRepositoryImpl(SpringDataPersonRepository dataPersonRepository) {
        this.dataPersonRepository = dataPersonRepository;
    }

    public Person save(Person person) {
        PersonEntity entity = PersonMapperRepo.personToEntity(person);

        PersonEntity savedEntity = dataPersonRepository.save(entity);

        Person savedPerson = PersonMapperRepo.entityToPerson(savedEntity);
        return savedPerson;
    }

}