package com.example.aftas.repository;

import com.example.aftas.SuppEntity.CompetitionMembres;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RankingRepository extends JpaRepository<Ranking,Long> {
    List<Ranking> findByCompetition(Competition competition);
    boolean existsByMembreAndCompetition(Member member,Competition competition);

}
