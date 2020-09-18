package tr.com.obss.jss.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tr.com.obss.jss.entity.Author;
import tr.com.obss.jss.entity.Book;
import tr.com.obss.jss.entity.Publisher;
import tr.com.obss.jss.entity.User;

import tr.com.obss.jss.repo.BookRepository;
import tr.com.obss.jss.service.AuthorService;
import tr.com.obss.jss.service.BookService;
import tr.com.obss.jss.service.PublisherService;
import tr.com.obss.jss.service.UserService;


import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ROLE_USER') or  hasRole('ROLE_ADMIN')")
public class UserController {

    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(userService.findAll()
        );
    }

    @GetMapping("/get_books")
    @ResponseBody
    public ResponseEntity<?> getBook(){
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/get_page")
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(name = "pageSize",defaultValue = "2")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(bookService.findAll(pageSize,pageNumber));
    }
    @GetMapping("/get_read_list")
    @ResponseBody
    public ResponseEntity<?> getRead_List(@RequestParam(name = "pageSize",defaultValue = "2")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(bookService.findAll(pageSize,pageNumber));
    }

    @GetMapping("/search_book")
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(name = "title", defaultValue = "") String title) {
        List<Book> bookList = bookService.findByTitle(title);
        return ResponseEntity.ok(bookList);
    }
    @GetMapping("/search_author")
    @ResponseBody
    public ResponseEntity<?> getAuthor(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Author> authorList = authorService.findByName(name);
        return ResponseEntity.ok(authorList);


    }

    @GetMapping("/search_publisher")
    @ResponseBody
    public ResponseEntity<?> getPublisher(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Publisher> publisherList = publisherService.findByName(name);
        return ResponseEntity.ok(publisherList);
    }
    @RequestMapping(method=RequestMethod.POST,value="/read_list/{bookId}")
    public ResponseEntity<?> readList(@PathVariable Long bookId, Principal p){

        Optional<User> userOpt = userService.findByUsername(p.getName());
        if(userOpt.isPresent()){
            Long id=userOpt.get().getId();
            User user = userService.addReaded(id,bookId);
            return  ResponseEntity.ok(user);
        }
        throw new IllegalArgumentException();
    }
    @RequestMapping(method=RequestMethod.POST,value="/fav_list/{bookId}")
    public ResponseEntity<?> favList(@PathVariable Long bookId,Principal p){
        Optional<User> userOpt = userService.findByUsername(p.getName());
        if (userOpt.isPresent()){
            Long id = userOpt.get().getId();
            User user = userService.addFavorite(id,bookId);
            return  ResponseEntity.ok(user);
        }
        throw new IllegalArgumentException();
    }

    @RequestMapping(method=RequestMethod.DELETE,value="/delete_fav_list/{bookId}")
    public ResponseEntity<?> deleteFavList(@PathVariable Long bookId,Principal p){
        Optional<User> userOpt = userService.findByUsername(p.getName());
        if (userOpt.isPresent()){
            Long id = userOpt.get().getId();
            User user = userService.deleteFavorite(id,bookId);
            return  ResponseEntity.ok(user);
        }
        throw new IllegalArgumentException();
    }
    @RequestMapping(method=RequestMethod.DELETE,value="/delete_read_list/{bookId}")
    public ResponseEntity<?> deleteReadList(@PathVariable Long bookId,Principal p){
        Optional<User> userOpt = userService.findByUsername(p.getName());
        if (userOpt.isPresent()){
            Long id = userOpt.get().getId();
            User user = userService.deleteReaded(id,bookId);
            return  ResponseEntity.ok(user);
        }
        throw new IllegalArgumentException();
    }




}
