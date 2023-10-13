package br.com.userapi.userapi.presentation.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.userapi.userapi.domain.enums.GenderEnum;
import br.com.userapi.userapi.presentation.annotation.EnumConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class PersonRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "lastname cannot be empty")
    private String lastname;

    @NotBlank(message = "personTaxId cannot be empty")
    private String personTaxId;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email invalid")
    private String email;

    @NotBlank(message = "cellphone cannot be empty")
    private String cellphone;

    @NotBlank(message = "gender cannot be empty")
    @EnumConstraint(enumClass = GenderEnum.class)
    private String gender;
}
