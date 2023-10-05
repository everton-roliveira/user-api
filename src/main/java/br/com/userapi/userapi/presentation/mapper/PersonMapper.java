package br.com.userapi.userapi.presentation.mapper;

import br.com.userapi.userapi.domain.entity.Person;
import br.com.userapi.userapi.domain.enums.GenderEnum;
import br.com.userapi.userapi.presentation.request.PersonRequest;
import br.com.userapi.userapi.presentation.request.PersonUpdateRequest;

public class PersonMapper {
    private static Person personRequestToPerson(PersonRequest request) {
        if (request == null) {
            return null;
        }

        return Person.builder()
        .name(request.getName())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .gender(GenderEnum.getValue(request.getGender() != null ? request.getGender().toString() : null))
        .personTaxId(request.getPersonTaxId())
        .phoneNumber(request.getPhoneNumber())
        .build();
    }

    public static Person personCreateRequestToPerson(PersonRequest request) {
        return personRequestToPerson(request);
    }

    public static Person personUpdateRequestToPerson(PersonUpdateRequest request, Long id) {
        Person person = personRequestToPerson(request);
        person.setId(id);
        return person;
    }
}
