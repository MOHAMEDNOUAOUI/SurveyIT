package com.wora.api_rest_survey_it.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {

    private LocalDateTime timestamps;
    private String message;
}
