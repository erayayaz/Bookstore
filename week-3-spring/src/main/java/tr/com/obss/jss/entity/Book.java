package tr.com.obss.jss.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.awt.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book extends EntityBase {
    @Column(name = "TITLE", length = 255, unique = true)
    private String title;

    @Column(name = "AUTHOR", length = 255)
    private String author;

    @Column(name = "PUBLISHER", length = 255)
    private String publisher;

    @Column(name = "PUBLICATION_YEAR", length = 255)
    private String publication_year;

    @ManyToMany(mappedBy = "readed_books" )
    @JsonBackReference
    private Set<User> readed_users;


    @ManyToMany(mappedBy = "fav_books" )
    @JsonBackReference
    private Set<User> fav_users;

    public Set<User> getFav_users() {
        return fav_users;
    }

    public void setFav_users(Set<User> fav_users) {
        this.fav_users = fav_users;
    }

    public Set<User> getReaded_users() {
        return readed_users;
    }

    public void setReaded_users(Set<User> readed_users) {
        this.readed_users = readed_users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }



}
