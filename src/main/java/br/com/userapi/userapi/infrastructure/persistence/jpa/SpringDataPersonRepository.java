package br.com.userapi.userapi.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.userapi.userapi.infrastructure.entity.PersonEntity;

public interface SpringDataPersonRepository extends JpaRepository<PersonEntity, Long>{
    
}
