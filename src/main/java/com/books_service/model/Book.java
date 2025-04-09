package com.books_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//3. Хранить поля: id, vendorCode, title, year, brand, stock, price.
// Все данные сохраняются в базу данных.

@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vendorCode;

    private String title;

    private int year;

    private String brand;

    private int stock;

    private double price;
}
