package tr.com.obss.jss.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserUpdateDto {


    @NotBlank
    @Size(max = 255, min = 3, message = "Lütfen geçerli bir şifre giriniz")
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
