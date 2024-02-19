package com.example.aftas.entity;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Hunting> huntings;
}
