package com.books_service.service;

import com.books_service.dto.BookDTO;
import com.books_service.model.Book;
import com.books_service.repository.BookRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void createBook(BookDTO createDTO) {
        checkIfExist(createDTO.getTitle(), createDTO.getVendorCode());
        Book book = new Book();
        book.updateBook(createDTO);
        bookRepository.save(book);
    }

    private void checkIfExist(String title, String code){
        if(bookRepository.existsBookByTitleIgnoreCase(title)){
            throw new EntityExistsException("Книга с таким названием уже существует");
        }
        if(bookRepository.existsBookByVendorCode(code)){
            throw new EntityExistsException("Книга с таким артикулом уже существует");
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBookById(Long id) {
        checkId(id);
        // достаем обьект из Optional сразу, потому что есть проверка на существование айди
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
    }

    public void updateBookById(Long id, BookDTO updateDTO) {
        checkId(id);
        checkIfExist(updateDTO.getTitle(), updateDTO.getVendorCode());
        // достаем обьект из Optional сразу, потому что есть проверка на существование айди
        Book book = bookRepository.findById(id).get();
        book.updateBook(updateDTO);
        bookRepository.save(book);
    }

    private void checkId(Long id){
        if(!bookRepository.existsBookById(id)){
            throw new EntityNotFoundException("Книга с таким айди не существует, операци не возможна");
        }
    }

    public Page<Book> findByFilters(String title, String brand, Integer year, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return bookRepository.findByFilters(title, brand, year, pageable);
    }
}
