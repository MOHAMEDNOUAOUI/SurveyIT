package com.wora.api_rest_survey_it.service.Impl;

import com.wora.api_rest_survey_it.DTO.Auth.LoginDTO;
import com.wora.api_rest_survey_it.DTO.Auth.RegisterDTO;
import com.wora.api_rest_survey_it.config.JwtTokenUtil;
import com.wora.api_rest_survey_it.entity.Enum.Rrole;
import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.mapper.AuthMapper;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;
    private final OwnerRepository ownerRepository;


    public String register(RegisterDTO registerDTO) {
        Optional<Owner> user = ownerRepository.findByUsername(registerDTO.getUsername());
        if (user.isPresent()){
            return "This user already exist";
        }
        Owner owner = authMapper.toEntity(registerDTO);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        owner.setRole(Rrole.Admin);
        try{
            Owner saved = ownerRepository.save(owner);
        }catch (Exception  e){
            System.out.println(e.getMessage());
        }
        return "Registred succefully";
    }


    public String login(LoginDTO loginDTO){
        Owner user = ownerRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));


        if (!passwordEncoder.matches(loginDTO.getPassword() , user.getPassword())){
            throw new RuntimeException("invalid Password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        loginDTO.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole().name()))
                )
        );


        String token =  jwtTokenUtil.generateToken(authentication);

        System.out.println(token);
        return token;
    }

}