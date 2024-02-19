package com.example.aftas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double averageWeight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leveloffish")
    private Level level;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Hunting> huntings;
}
