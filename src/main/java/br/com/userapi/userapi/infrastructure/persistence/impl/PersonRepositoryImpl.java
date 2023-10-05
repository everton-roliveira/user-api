package br.com.userapi.userapi.infrastructure.persistence.impl;

import org.springframework.stereotype.Repository;

import br.com.userapi.userapi.domain.entity.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    @Override
    public Person save(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
