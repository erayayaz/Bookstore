package tr.com.obss.jss.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleOrderByAuthor (String title);



}
