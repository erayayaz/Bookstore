package tr.com.obss.jss.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User extends EntityBase {

    @Column(name = "USERNAME", length = 255, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
    joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private List<Role> roles;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "READ_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private Set<Book> readed_books;



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "FAVORITE_LIST",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID", referencedColumnName = "ID")})
    @JsonManagedReference
    private Set<Book> fav_books;

    public Set<Book> getReaded_books() {
        return readed_books;
    }

    public void setReaded_books(Book readed_books) {
        this.readed_books.add(readed_books);
    }


    public Set<Book> getFav_books() {
        return fav_books;
    }

    public void setFav_books(Book fav_books) {
        this.fav_books.add(fav_books);
    }
    public void deleteFav_books(Book book) {
        this.fav_books.remove(book);
        book.getFav_users().remove(this);
    }
    public void deleteReaded_books(Book book) {
        this.readed_books.remove(book);
        book.getReaded_users().remove(this);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
