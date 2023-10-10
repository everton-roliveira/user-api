package br.com.userapi.userapi.presentation.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.userapi.userapi.domain.entity.Person;
import br.com.userapi.userapi.presentation.mapper.PersonMapper;
import br.com.userapi.userapi.presentation.request.PersonCreateRequest;
import br.com.userapi.userapi.usecase.PersonUseCase;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Valid PersonCreateRequest request) {

        Person person = PersonMapper.personCreateRequestToPerson(request);
        Person createdPerson = personUseCase.createPerson(person);
        return new ResponseEntity<>(createdPerson, HttpStatus.CREATED);
    }
}