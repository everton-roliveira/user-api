package br.com.userapi.userapi.presentation.response;

import br.com.userapi.userapi.domain.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonResponse {

    private Integer id;
    private String name;
    private String lastname;
    private String personTaxId;
    private String email;
    private String cellphone;
    private GenderEnum gender;
}

