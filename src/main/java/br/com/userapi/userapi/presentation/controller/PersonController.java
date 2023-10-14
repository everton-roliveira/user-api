package br.com.userapi.userapi.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.presentation.mapper.PersonMapper;
import br.com.userapi.userapi.presentation.request.PersonCreateRequest;
import br.com.userapi.userapi.presentation.response.PersonResponse;
import br.com.userapi.userapi.usecase.CreatePersonUseCase;
import br.com.userapi.userapi.usecase.GetPersonByIdUseCase;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final CreatePersonUseCase createPersonUseCase;
    private final GetPersonByIdUseCase getPersonByIdUseCase;

    public PersonController(CreatePersonUseCase createPersonUseCase, GetPersonByIdUseCase getPersonByIdUseCase) {
        this.createPersonUseCase = createPersonUseCase;
        this.getPersonByIdUseCase = getPersonByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid PersonCreateRequest request) {

        Person person = PersonMapper.personCreateRequestToPerson(request);
        Person createdPerson = createPersonUseCase.execute(person);

        return new ResponseEntity<>(PersonMapper.personToPersonResponse(createdPerson), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable Integer id) {
        Person getPersonById = getPersonByIdUseCase.execute(id);

        return new ResponseEntity<PersonResponse>(PersonMapper.personToPersonResponse(getPersonById), HttpStatus.OK);
    }
}