package tr.com.obss.jss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class Publisher extends EntityBase {

    @Column(name = "NAME", length = 255, unique = true)
    private String name;

    @Column(name = "ADDRESS", length = 255)
    private String address;

    @Column(name = "PHONE", length = 11)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
