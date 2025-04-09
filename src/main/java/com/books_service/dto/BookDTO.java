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
    @NotNull(message = "Артикул не должен быть пустым")
    private String vendorCode;
    
    @NotBlank(message = "Название не может быть пустым")
    private String title;
    
    @NotNull(message = "Год не должен быть пустым")
    @Positive(message = "Год не может быть отрицательным")
    @Max(value = 2025, message = "Год не может быть больше текущего")
    private Integer year;

    @NotBlank(message = "Бренд не может быть пустым")
    private String brand;

    @NotNull(message = "Количество не должен быть пустым")
    @Positive(message = "Количество не может быть отрицательным")
    private Integer stock;

    @NotNull(message = "Price не должен быть пустым")
    @Positive(message = "Price не может быть отрицательным")
    private Double price;
}
