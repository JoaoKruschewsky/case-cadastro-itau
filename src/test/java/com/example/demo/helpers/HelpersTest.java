package com.example.demo.helpers;

import com.example.demo.adapter.dto.AddressDTO;
import com.example.demo.adapter.dto.RegisterUserRequest;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

public class HelpersTest {


    public static RegisterUserRequest buildRequest (String name) {
        return new RegisterUserRequest(
                name,
                "12345678901",
                "teste@email.com",
                "2000-01-01",
                "01001000",
                new AddressDTO("Rua Teste", "Bairro", "São Paulo", "SP")

        );
    }


}
