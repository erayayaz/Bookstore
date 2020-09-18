package tr.com.obss.jss.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PublisherUpdateDto {
    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir adres giriniz")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
