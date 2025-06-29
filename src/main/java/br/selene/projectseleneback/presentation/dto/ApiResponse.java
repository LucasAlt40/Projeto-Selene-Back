package br.selene.projectseleneback.presentation.dto;

public record ApiResponse<T>(
        Boolean success,
        String message,
        T data,
        Object metadata
) {
}
