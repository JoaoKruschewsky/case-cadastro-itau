package com.example.demo.adapter.in.mapper;

import com.example.demo.adapter.dto.ApiResponseDTO;

public class ApiResponseMapper {


    public static <T> ApiResponseDTO<T> parseToApiResponseSuccess(T body) {

        return new ApiResponseDTO<>(
                "Login gerado com sucesso!",
                body
        );

    }
}
