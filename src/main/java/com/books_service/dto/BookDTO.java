package com.books_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    @NotNull(message = "Артикул обязателен")
    @NotBlank(message = "Артикул не должен быть пустым")
    private String vendorCode;

    @NotNull(message = "Название обязательно")
    @NotBlank(message = "Название не может быть пустым")
    private String title;
    
    @NotNull(message = "Год обязателен")
    @Positive(message = "Год не может быть отрицательным")
    @Max(value = 2025, message = "Год не может быть больше текущего")
    private Integer year;

    @NotNull(message = "Бренд обязателен")
    @NotBlank(message = "Бренд не может быть пустым")
    private String brand;

    @NotNull(message = "Количество обязательно")
    @Positive(message = "Количество не может быть отрицательным")
    private Integer stock;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена не может быть отрицательным")
    private Double price;
}
