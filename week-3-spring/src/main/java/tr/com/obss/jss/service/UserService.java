package tr.com.obss.jss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.entity.Book;
import tr.com.obss.jss.entity.User;
import tr.com.obss.jss.model.MyUserDetails;
import tr.com.obss.jss.model.UserDTO;
import tr.com.obss.jss.model.UserUpdateDto;
import tr.com.obss.jss.repo.BookRepository;
import tr.com.obss.jss.repo.RoleRepository;
import tr.com.obss.jss.repo.UserRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public User save(UserDTO userDto){
        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        User savedUser = userRepository.save(user);

        return savedUser;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public Optional<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }

    public Page<User> findAll(int pageSize, int pageNumber){
        Pageable paged = PageRequest.of(pageNumber,pageSize);
        return userRepository.findAll(paged);
    }
    public User addReaded(long id,long bookId){
        Optional<User> byId =userRepository.findById(id);
        Optional<Book> byBookId = bookRepository.findById(bookId);
        if(byId.isPresent() && byBookId.isPresent()){
            User user = byId.get();
            Book book = byBookId.get();
            user.setReaded_books(book);

            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }
    public User addFavorite(long id,long bookId){
        Optional<User> byId =userRepository.findById(id);
        Optional<Book> byBookId = bookRepository.findById(bookId);
        if(byId.isPresent() && byBookId.isPresent()){
            User user = byId.get();
            Book book = byBookId.get();
            user.setFav_books(book);

            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }
    public User deleteFavorite(long id,long bookId){
        Optional<User> byId =userRepository.findById(id);
        Optional<Book> byBookId = bookRepository.findById(bookId);
        if(byId.isPresent() && byBookId.isPresent()){
            User user = byId.get();
            Book book = byBookId.get();

            user.deleteFav_books(book);

            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }
    public User deleteReaded(long id,long bookId){
        Optional<User> byId =userRepository.findById(id);
        Optional<Book> byBookId = bookRepository.findById(bookId);
        if(byId.isPresent() && byBookId.isPresent()){
            User user = byId.get();
            Book book = byBookId.get();

            user.deleteReaded_books(book);

            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }
    public User update(long id, UserUpdateDto dto){
        Optional<User> byId =userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            user.setPassword(encoder.encode(dto.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    public User delete(long id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            user.setActive(!user.isActive());
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }


    @Override
    public UserDetails loadUserByUsername(String username){
            Optional<User> byUsername = userRepository.findByUsername(username);
            if(byUsername.isPresent()){
                return new MyUserDetails(byUsername.get());
            }
            throw new UsernameNotFoundException("Kullanıcı olmadı");
    }
}
