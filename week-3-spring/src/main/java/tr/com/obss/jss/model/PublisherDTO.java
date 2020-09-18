package tr.com.obss.jss.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PublisherDTO {
    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir şirket ismi giriniz")
    private String name;

    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir adres giriniz")
    private String address;

    @NotBlank
    @Size(max=11,min=7,message = "Lütfen geçerli bir numara giriniz")
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
