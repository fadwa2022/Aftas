package com.example.aftas.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numberOfFish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membre_id")
    private Member membre;
    @ManyToOne
    @JoinColumn(name = "competitionofhunting")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "fishofhunting")
    private Fish fish;
}
