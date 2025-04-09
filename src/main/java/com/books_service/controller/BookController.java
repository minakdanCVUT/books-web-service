package com.books_service.controller;

import com.books_service.dto.BookDTO;
import com.books_service.model.Book;
import com.books_service.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//6. REST API: GET /api/books,
// POST /api/books,
// PUT /api/books/{id},
// DELETE /api/books/{id}

@Controller
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping
    public String getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer year,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        Page<Book> books = bookService.findByFilters(title, brand, year, page - 1, 10);
        model.addAttribute("books", books);
        return "books/list";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@Valid @RequestBody BookDTO createDTO){
        bookService.createBook(createDTO);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBookById(@PathVariable Long id,
                               @Valid @RequestBody BookDTO updateDTO){
        bookService.updateBookById(id, updateDTO);
    }
}
