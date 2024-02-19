package com.example.aftas.repository;

import com.example.aftas.SuppEntity.CompetitionMembres;
import com.example.aftas.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompetitionMembresRepository extends JpaRepository<CompetitionMembres,Long> {
    List<CompetitionMembres> findByCompetition(Competition competition);

}
