package com.wora.api_rest_survey_it.config;

import com.wora.api_rest_survey_it.entity.Owner;
import com.wora.api_rest_survey_it.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByName(username)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur not found"));
        return new User(
                owner.getName(),
                owner.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(owner.getRole().name()))
        );
    }
}