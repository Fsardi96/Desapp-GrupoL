package ar.edu.unq.desapp.grupoL.backenddesappapi.model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder


public class TokenDTO {
    private String token;
}
