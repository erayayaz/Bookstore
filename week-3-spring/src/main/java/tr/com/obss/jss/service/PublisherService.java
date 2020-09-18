package tr.com.obss.jss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tr.com.obss.jss.entity.Publisher;
import tr.com.obss.jss.model.PublisherDTO;
import tr.com.obss.jss.model.PublisherUpdateDto;
import tr.com.obss.jss.repo.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> findByName(String name){
        return publisherRepository.findByName(name);
    }

    public Publisher save(PublisherDTO publisherDTO){
        Publisher publisher = new Publisher();

        publisher.setName(publisherDTO.getName());
        publisher.setAddress(publisherDTO.getAddress());
        publisher.setPhone(publisherDTO.getPhone());

        Publisher savedPublisher = publisherRepository.save(publisher);

        return savedPublisher;
    }

    public Publisher delete (Long id){
        Optional<Publisher> byId = publisherRepository.findById(id);
        if(byId.isPresent()){
            Publisher publisher = byId.get();
            publisher.setActive(!publisher.isActive());
            return publisherRepository.save(publisher);
        }
        throw new IllegalArgumentException();
    }

    public Publisher update(long id, PublisherUpdateDto dto){
        Optional<Publisher> byId =publisherRepository.findById(id);
        if(byId.isPresent()){
            Publisher publisher= byId.get();
            publisher.setAddress(dto.getAddress());
            return publisherRepository.save(publisher);
        }
        throw new IllegalArgumentException();
    }
    public Page<Publisher> findAll(int pageSize, int pageNumber){
        Pageable paged = PageRequest.of(pageNumber,pageSize);
        return publisherRepository.findAll(paged);
    }
}
