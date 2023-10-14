package br.com.userapi.userapi.domain.usecase;

public interface IUseCase<Input, Output> {
    Output execute(Input input);
}
