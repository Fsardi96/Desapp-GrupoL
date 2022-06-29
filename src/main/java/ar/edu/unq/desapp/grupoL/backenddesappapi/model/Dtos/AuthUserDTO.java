package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;


import ar.edu.unq.desapp.grupoL.backenddesappapi.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
public class AuthUserDTO {

    @JsonProperty
    public String name;

    @JsonProperty
    public String password;

    public static AuthUserDTO from(User user) {
        return new AuthUserDTO(user.getName(), user.getPassword());
    }

    public AuthUserDTO(String name, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        this.name = name;
        this.password = encodedPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}