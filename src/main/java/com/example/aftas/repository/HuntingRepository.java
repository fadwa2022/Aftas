package com.example.aftas.repository;

import com.example.aftas.dto.Response.HuntingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Fish;
import com.example.aftas.entity.Hunting;
import com.example.aftas.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntingRepository extends JpaRepository<Hunting,Long> {
    List<Hunting> findByCompetition(Competition competition);
    List<Hunting> findHuntingByCompetitionAndMembre(Competition competition, Member member);
    Hunting findHuntingByCompetitionAndMembreAndFish(Competition competition, Member member, Fish fish);

}
