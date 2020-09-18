package tr.com.obss.jss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author extends EntityBase {


    @Column(name = "NAME", length = 255)
    private String name;

    @Column(name = "SURNAME", length = 255)
    private String surname;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
