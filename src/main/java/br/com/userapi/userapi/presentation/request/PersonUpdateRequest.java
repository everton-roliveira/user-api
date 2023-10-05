package br.com.userapi.userapi.presentation.request;

import br.com.userapi.userapi.domain.enums.GenderEnum;

public class PersonUpdateRequest extends PersonRequest {

    public PersonUpdateRequest(String name, String lastname, String phoneNumber, GenderEnum gender) {
        super(name, lastname, null, null, phoneNumber, gender);
    }
}
