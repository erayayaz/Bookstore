package tr.com.obss.jss.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
        List<Author> findByName(String name);
}
