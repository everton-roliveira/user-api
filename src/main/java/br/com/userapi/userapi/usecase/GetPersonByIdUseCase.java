package br.com.userapi.userapi.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;
import br.com.userapi.userapi.domain.usecase.IUseCase;
import br.com.userapi.userapi.presentation.exception.NotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetPersonByIdUseCase implements IUseCase<Integer, Person> {

    @Autowired
    private final PersonRepository personRepository;

    /**
     * Retrieve a person by their ID.
     *
     * @param id The ID of the person to retrieve.
     * @return The person with the specified ID.
     * @throws NotFoundException if the person is not found.
     */
    @Override
    public Person execute(Integer id) {
        Person person = personRepository.getById(id);

        if (person == null) {
            throw new NotFoundException("Person not found");
        }

        return person;
    }

}
