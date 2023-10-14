package br.com.userapi.userapi.infrastructure.persistence.impl;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import br.com.userapi.userapi.domain.model.Person;
import br.com.userapi.userapi.domain.repository.PersonRepository;
import br.com.userapi.userapi.infrastructure.entity.PersonEntity;
import br.com.userapi.userapi.infrastructure.mapper.PersonMapperRepo;
import br.com.userapi.userapi.infrastructure.persistence.jpa.SpringDataPersonRepository;
import br.com.userapi.userapi.presentation.exception.ConflictException;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final SpringDataPersonRepository dataPersonRepository;
    private final String constraintEmail = "person_tbl_uk_email";
    private final String constraintCellphone = "person_tbl_uk_cellphone";
    private final String constraintPersonTaxId = "person_tbl_uk_personTaxId";

    public PersonRepositoryImpl(SpringDataPersonRepository dataPersonRepository) {
        this.dataPersonRepository = dataPersonRepository;
    }

    public Person save(Person person) {
        try {
            PersonEntity entity = PersonMapperRepo.personToEntity(person);
            PersonEntity savedEntity = dataPersonRepository.save(entity);
            return PersonMapperRepo.entityToPerson(savedEntity);
        } catch (DataIntegrityViolationException ex) {

            String constraintName = "";

            if (ex.getMostSpecificCause() instanceof PSQLException) {
                PSQLException sqlException = (PSQLException) ex.getMostSpecificCause();
                constraintName = sqlException.getServerErrorMessage().getConstraint();
            }
            if (constraintEmail.equalsIgnoreCase(constraintName)) {
                throw new ConflictException("Email is already in use.", "email", person.getEmail());
            } else if (constraintCellphone.equalsIgnoreCase(constraintName)) {
                throw new ConflictException("Phone number is already in use.", "cellphone", person.getCellphone());
            } else if (constraintPersonTaxId.equalsIgnoreCase(constraintName)) {
                throw new ConflictException("Person Tax ID is already in use.", "personTaxId", person.getPersonTaxId());
            } else {
                throw new ConflictException("A resource with the same unique key already exists.", "unknown", null);
            }
        }
    }

}