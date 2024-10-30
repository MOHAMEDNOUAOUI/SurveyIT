package com.wora.api_rest_survey_it.DTO.Owner.embd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponseCreate {

    private Long id;
    private String name;
}
