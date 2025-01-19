package com.wora.api_rest_survey_it.entity;

import com.wora.api_rest_survey_it.entity.Enum.Rrole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rrole role;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE , orphanRemoval = true)
    private List<Survey> survey = new ArrayList<>();
}
