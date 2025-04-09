package com.books_service.repository;

import com.books_service.model.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByTitleIgnoreCase(String title);

    boolean existsBookByVendorCode(String code);

    boolean existsBookById(Long id);

    @Query("SELECT b FROM Book b WHERE " +
            "(:#{#title} IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :#{#title}, '%'))) AND " +
            "(:#{#brand} IS NULL OR LOWER(b.brand) LIKE LOWER(CONCAT('%', :#{#brand}, '%'))) AND " +
            "(:#{#year} IS NULL OR b.publicationYear = :#{#year})")
    Page<Book> findByFilters(
            @Param("title") String title,
            @Param("brand") String brand,
            @Param("year") Integer year,
            Pageable pageable
    );
}
