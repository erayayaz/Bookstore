package tr.com.obss.jss.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public class BookDTO {
    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir kitap giriniz")
    private String title;

    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir isim giriniz")
    private String author;

    @NotBlank
    @Size(max=255,min=3,message = "Lütfen geçerli bir yayınevi giriniz")
    private String publisher;

    @NotBlank
    @Size(max=255,min=3, message = "Giriceğiniz kitabın bsaım yılı 2020den düşük olması lazım.")
    private String publication_year;

    public String getPublication_year() {
        return publication_year;
    }

    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
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
}
