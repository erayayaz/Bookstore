package tr.com.obss.jss.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.obss.jss.entity.Publisher;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
    List<Publisher> findByName(String name);
}
