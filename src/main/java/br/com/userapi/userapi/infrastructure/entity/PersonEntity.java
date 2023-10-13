package br.com.userapi.userapi.infrastructure.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "person_tbl")
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "person_tax_id", nullable = false, length = 14)
    private String personTaxId;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone_number", length = 11, unique = true)
    private String cellphone;

    @Column(name = "gender", length = 1)
    private char gender;

    @Column(name= "deleted_at")
    private Date deletedAt;
}