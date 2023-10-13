package br.com.userapi.userapi.infrastructure.mapper;

import org.springframework.stereotype.Component;

import br.com.userapi.userapi.domain.enums.GenderEnum;
import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.infrastructure.entity.PersonEntity;

@Component
public class PersonMapperRepo {
    public static PersonEntity personToEntity(Person person) {
        if (person == null) {
            return null;
        }

        return PersonEntity.builder()
        .id(person.getId())
        .name(person.getName())
        .personTaxId(person.getPersonTaxId())
        .lastname(person.getLastname())
        .email(person.getEmail())
        .gender(person.getGender().getValue())
        .cellphone(person.getCellphone())
        .build();
    }

    public static Person entityToPerson(PersonEntity entity) {
        if (entity == null) {
            return null;
        }

        return Person.builder()
        .id(entity.getId())
        .name(entity.getName())
        .lastname(entity.getLastname())
        .personTaxId(entity.getPersonTaxId())
        .email(entity.getEmail())
        .gender(GenderEnum.fromValue(entity.getGender()))
        .cellphone(entity.getCellphone())
        .build();
    }
}
