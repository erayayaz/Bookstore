package tr.com.obss.jss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.com.obss.jss.entity.Author;
import tr.com.obss.jss.entity.Book;
import tr.com.obss.jss.entity.Publisher;
import tr.com.obss.jss.entity.User;
import tr.com.obss.jss.model.*;
import tr.com.obss.jss.service.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private BookService bookService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /*Start Admin Part*/
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<?> post(@Valid @RequestBody UserDTO userDto) {
        User admin = adminService.save(userDto);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(adminService.findAll());
    }

    /*End Admin Part*/
    /*---------------*/
    /* Start User Part */
    @PostMapping("/add_user")
    @ResponseBody
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDTO userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search_user")
    @ResponseBody
    public ResponseEntity<?> getUser(@RequestParam(name = "username", defaultValue = "") String username) {
        Optional<User> userList = userService.findByUsername(username);
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/delete_user/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Long id) {

        User user = userService.delete(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update_user/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDto userDto) {

        User user = userService.update(id, userDto);
        return ResponseEntity.ok(user);
    }
    /*End User Part*/
    /*---------------*/
    /*Start Book Part*/

    @PostMapping("/add_book")
    @ResponseBody
    public ResponseEntity<?> post(@Valid @RequestBody BookDTO bookDto) {
        Book book = bookService.save(bookDto);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search_book")
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(name = "title", defaultValue = "") String title) {
        List<Book> bookList = bookService.findByTitle(title);
        return ResponseEntity.ok(bookList);
    }

    @DeleteMapping("/delete_book/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        Book book = bookService.delete(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/update_book/{id}")
    @ResponseBody
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookUpdateDto bookDto) {

        Book book = bookService.update(id, bookDto);
        return ResponseEntity.ok(book);
    }

    /*End Book Part*/
    /*---------------*/
    /*Start Author Part*/
    @PostMapping("/add_author")
    @ResponseBody
    public ResponseEntity<?> post(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = authorService.save(authorDTO);
        return ResponseEntity.ok(author);
    }

    @GetMapping("/search_author")
    @ResponseBody
    public ResponseEntity<?> getAuthor(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Author> authorList = authorService.findByName(name);
        return ResponseEntity.ok(authorList);
    }

    @DeleteMapping("/delete_author/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {

        Author author = authorService.delete(id);
        return ResponseEntity.ok(author);
    }

    @PutMapping("/update_author/{id}")
    @ResponseBody
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorUpdateDto authorUpdateDto) {

        Author author = authorService.update(id, authorUpdateDto);
        return ResponseEntity.ok(author);
    }

    /*End Author Part*/
    /*---------------*/
    /*Start Publisher Part*/
    @PostMapping("/add_publisher")
    @ResponseBody
    public ResponseEntity<?> postPublisher(@Valid @RequestBody PublisherDTO publisherDTO) {
        Publisher publisher = publisherService.save(publisherDTO);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping("/search_publisher")
    @ResponseBody
    public ResponseEntity<?> getPublisher(@RequestParam(name = "name", defaultValue = "") String name) {
        List<Publisher> publisherList = publisherService.findByName(name);
        return ResponseEntity.ok(publisherList);
    }

    @DeleteMapping("/delete_publisher/{id}")
    @ResponseBody
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {

        Publisher publisher = publisherService.delete(id);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/update_publisher/{id}")
    @ResponseBody
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @Valid @RequestBody PublisherUpdateDto publisherUpdateDto) {

        Publisher publisher = publisherService.update(id, publisherUpdateDto);
        return ResponseEntity.ok(publisher);
    }
    /*End Publisher Part*/

    /*Start Page*/
    @GetMapping("/get_user_page")
    @ResponseBody
    public ResponseEntity<?> get(@RequestParam(name = "pageSize",defaultValue = "5")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(userService.findAll(pageSize,pageNumber));
    }

    @GetMapping("/get_book_page")
    @ResponseBody
    public ResponseEntity<?> getBooks(@RequestParam(name = "pageSize",defaultValue = "5")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(bookService.findAll(pageSize,pageNumber));
    }

    @GetMapping("/get_author_page")
    @ResponseBody
    public ResponseEntity<?> getAuthors(@RequestParam(name = "pageSize",defaultValue = "5")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(authorService.findAll(pageSize,pageNumber));
    }

    @GetMapping("/get_publisher_page")
    @ResponseBody
    public ResponseEntity<?> getPublishers(@RequestParam(name = "pageSize",defaultValue = "5")int pageSize,
                                 @RequestParam(name = "pageNumber", defaultValue = "0")int pageNumber){

        return ResponseEntity.ok(publisherService.findAll(pageSize,pageNumber));
    }


    /*End Page*/

}
