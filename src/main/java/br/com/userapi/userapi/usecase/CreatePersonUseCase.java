package br.com.userapi.userapi.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;
import br.com.userapi.userapi.domain.usecase.IUseCase;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CreatePersonUseCase implements IUseCase<Person, Person> {
    @Autowired
    private final PersonRepository personRepository;

    public Person execute(Person person) {
        return personRepository.save(person);
    }
}
