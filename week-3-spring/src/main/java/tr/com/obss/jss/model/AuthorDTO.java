package tr.com.obss.jss.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthorDTO {
    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir isim giriniz")
    private String name;

    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir soyisim giriniz")
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
