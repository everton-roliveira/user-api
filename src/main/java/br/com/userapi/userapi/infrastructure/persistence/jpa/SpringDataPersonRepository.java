package br.com.userapi.userapi.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.userapi.userapi.domain.entity.Person;

public interface SpringDataPersonRepository extends JpaRepository<Person, Long>{
    
}
