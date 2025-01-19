package com.wora.api_rest_survey_it.controller;


import com.wora.api_rest_survey_it.DTO.Auth.LoginDTO;
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

        if (result.equals("This user already exist")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        } else if (result.equals("Registered successfully")) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        String result = authService.login(loginDTO);
        if (result.equals("the user does exist")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }else if (result.equals("Password is not correct")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }


}
