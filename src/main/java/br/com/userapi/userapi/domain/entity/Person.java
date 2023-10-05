package br.com.userapi.userapi.domain.entity;

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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "lastname", nullable = false, length = 255)
    private String lastname;

    @Column(name = "person_tax_id", nullable = false, length = 14)
    private String personTaxId;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone_number", length = 11, unique = true)
    private String phoneNumber;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name= "deleted_at")
    private Date deletedAt;
}