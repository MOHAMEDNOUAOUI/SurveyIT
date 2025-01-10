package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Auth.RegisterDTO;
import com.wora.api_rest_survey_it.service.Impl.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        String result = authService.register(registerDTO);

        if (result.equals("This user already exists")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        } else if (result.equals("Registered successfully")) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
    }

}
