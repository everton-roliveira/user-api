package br.com.userapi.userapi.presentation.request;

public class PersonUpdateRequest extends PersonRequest {

    public PersonUpdateRequest(String name, String lastname, String phoneNumber, String gender) {
        super(name, lastname, null, null, phoneNumber, gender);
    }
}
