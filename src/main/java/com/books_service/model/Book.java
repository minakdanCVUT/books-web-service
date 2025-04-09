package com.books_service.model;

import com.books_service.dto.BookDTO;
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

    private Integer publicationYear;

    private String brand;

    private Integer stock;

    private Double price;

    public void updateBook(BookDTO dto){
        this.vendorCode = dto.getVendorCode();
        this.brand = dto.getBrand();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
        this.title = dto.getTitle();
        this.publicationYear = dto.getPublicationYear();
    }
}
