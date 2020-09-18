package tr.com.obss.jss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.entity.Book;
import tr.com.obss.jss.model.BookDTO;
import tr.com.obss.jss.model.BookUpdateDto;
import tr.com.obss.jss.repo.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public List<Book> findByTitle(String title){
        return bookRepository.findByTitleOrderByAuthor(title);
    }
    public Book save(BookDTO bookDto){
        Book book = new Book();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setPublication_year(bookDto.getPublication_year());

        Book savedBook = bookRepository.save(book);

        return savedBook;
    }
    public Page<Book> findAll(int pageSize, int pageNumber){
        Pageable paged = PageRequest.of(pageNumber,pageSize);
        return bookRepository.findAll(paged);
    }
    public Book delete(long id){
        Optional<Book> byId = bookRepository.findById(id);
        if(byId.isPresent()){
            Book book = byId.get();
            book.setActive(!book.isActive());
            return bookRepository.save(book);
        }
        throw new IllegalArgumentException();
    }
    public Book update(long id, BookUpdateDto dto){
        Optional<Book> byId =bookRepository.findById(id);
        if(byId.isPresent()){
            Book book= byId.get();
            book.setPublisher(dto.getPublisher());
            return bookRepository.save(book);
        }
        throw new IllegalArgumentException();
    }
}
