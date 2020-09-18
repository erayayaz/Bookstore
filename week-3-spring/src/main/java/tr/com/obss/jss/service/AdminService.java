package tr.com.obss.jss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.entity.User;
import tr.com.obss.jss.model.UserDTO;
import tr.com.obss.jss.repo.RoleRepository;
import tr.com.obss.jss.repo.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class AdminService{
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder1;

    @Bean
    public PasswordEncoder encoder1(){
        return new BCryptPasswordEncoder();
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(UserDTO userDto){
        User admin = new User();

        admin.setUsername(userDto.getUsername());
        admin.setPassword(encoder1.encode(userDto.getPassword()));
        admin.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
        User savedAdmin = userRepository.save(admin);

        return savedAdmin;
    }

}
