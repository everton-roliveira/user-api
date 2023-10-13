package br.com.userapi.userapi.domain.repository;

import br.com.userapi.userapi.domain.model.Person;

public interface PersonRepository {
    Person save(Person person);
}
