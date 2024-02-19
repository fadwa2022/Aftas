package com.example.aftas.SuppEntity;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "competition_membres")
public class CompetitionMembres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competition_code", referencedColumnName = "code")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "membres_num", referencedColumnName = "num")
    private Member member;


}

