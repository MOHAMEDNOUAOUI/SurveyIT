package com.wora.api_rest_survey_it.DTO.Auth;

import com.wora.api_rest_survey_it.entity.Enum.Rrole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    private Rrole role = Rrole.ROLE_Owner;
}
