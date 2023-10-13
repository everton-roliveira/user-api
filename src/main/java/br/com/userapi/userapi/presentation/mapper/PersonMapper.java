package br.com.userapi.userapi.presentation.mapper;

import br.com.userapi.userapi.domain.enums.GenderEnum;
import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.presentation.request.PersonRequest;
import br.com.userapi.userapi.presentation.request.PersonUpdateRequest;
import br.com.userapi.userapi.presentation.response.PersonResponse;
import br.com.userapi.userapi.util.formater.NumberFormatter;
import br.com.userapi.userapi.util.formater.PersonTaxIdFormatter;
import br.com.userapi.userapi.util.formater.PhoneFormatter;

public class PersonMapper {
    private static Person personRequestToPerson(PersonRequest request) {
        if (request == null) {
            return null;
        }

        return Person.builder()
        .name(request.getName())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .gender(GenderEnum.valueOf(request.getGender()))
        .personTaxId(NumberFormatter.onlyNumber(request.getPersonTaxId()))
        .cellphone(NumberFormatter.onlyNumber(request.getCellphone()))
        .build();
    }

    public static Person personCreateRequestToPerson(PersonRequest request) {
        return personRequestToPerson(request);
    }

    public static Person personUpdateRequestToPerson(PersonUpdateRequest request, Integer id) {
        Person person = personRequestToPerson(request);
        person.setId(id);
        return person;
    }

    public static PersonResponse personToPersonResponse(Person person) {
        if (person == null) {
            return null;
        }

        return PersonResponse.builder()
        .id(person.getId())
        .name(person.getName())
        .lastname(person.getLastname())
        .email(person.getEmail())
        .personTaxId(PersonTaxIdFormatter.personTaxIdMask(person.getPersonTaxId()))
        .cellphone(PhoneFormatter.cellphoneMask(person.getCellphone()))
        .gender(person.getGender())
        .build();

    }
}
