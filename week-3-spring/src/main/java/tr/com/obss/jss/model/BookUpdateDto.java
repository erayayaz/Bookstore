package tr.com.obss.jss.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookUpdateDto {
    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir yayınevi giriniz")
    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
