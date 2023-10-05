package br.com.userapi.userapi.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.userapi.userapi.domain.entity.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonUseCase {
    @Autowired
    private final PersonRepository personRepository;

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
}
