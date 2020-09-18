package tr.com.obss.jss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.entity.Author;
import tr.com.obss.jss.model.AuthorDTO;
import tr.com.obss.jss.model.AuthorUpdateDto;
import tr.com.obss.jss.repo.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findByName(String name){
        return authorRepository.findByName(name);
    }

    public Author save(AuthorDTO authorDTO){
        Author author = new Author();

        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());

        Author savedAuthor = authorRepository.save(author);

        return savedAuthor;
    }

    public Author delete (Long id){
        Optional<Author> byId = authorRepository.findById(id);
        if(byId.isPresent()){
            Author author = byId.get();
            author.setActive(!author.isActive());
            return authorRepository.save(author);
        }
        throw new IllegalArgumentException();
    }

    public Author update(long id, AuthorUpdateDto dto){
        Optional<Author> byId =authorRepository.findById(id);
        if(byId.isPresent()){
            Author author= byId.get();
            author.setSurname(dto.getSurname());
            return authorRepository.save(author);
        }
        throw new IllegalArgumentException();
    }
    public Page<Author> findAll(int pageSize, int pageNumber){
        Pageable paged = PageRequest.of(pageNumber,pageSize);
        return authorRepository.findAll(paged);
    }


}
