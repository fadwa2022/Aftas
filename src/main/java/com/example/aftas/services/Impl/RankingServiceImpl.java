package com.example.aftas.services.Impl;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.dto.Response.MemberDtoResponse;
import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Hunting;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;
import com.example.aftas.exception.CompetitionException;
import com.example.aftas.mapper.MaperRequest_Response;
import com.example.aftas.mapper.Mapper;

import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository repository;
    private Mapper<Ranking, RankingDtoResponse> mapper;
    private CompetitionRepository competitionRepository;
    private HuntingRepository huntingRepository;
    @Autowired
    public RankingServiceImpl(
            RankingRepository repository,
            Mapper<Ranking, RankingDtoResponse> mapper,
            CompetitionRepository competitionRepository,
            HuntingRepository huntingRepository
    ){
        this.repository=repository;
        this.mapper=mapper;
        this.competitionRepository=competitionRepository;
        this.huntingRepository=huntingRepository;
    }
    @Override
    public List<Ranking> calculeScors(String compititionCode) {
       Optional<Competition> competition =competitionRepository.findByCode(compititionCode);
         List<Hunting> HuntingOfCompitition=huntingRepository.findByCompetition(competition.get());
         List<Ranking> rankingOfCompitition=repository.findByCompetition(competition.get());
        for (Ranking ranking : rankingOfCompitition) {
            Integer scorOfMember =0;

        for (Hunting hunting : HuntingOfCompitition) {

            if (hunting.getMembre().getIdentityNumber().equals(ranking.getMembre().getIdentityNumber())) {

                 scorOfMember +=scorOfMember+(hunting.getNumberOfFish()*hunting.getFish().getLevel().getPoints());

            }


        }
        ranking.setScore(scorOfMember);
        repository.save(ranking);
        }

            return repository.findByCompetition(competition.get());
    }

    @Override
    public List<RankingDtoResponse> calculeRanking(String compititionCode) {

        Optional<Competition> competition =competitionRepository.findByCode(compititionCode);


        if (competition.isPresent()) {
            List<Ranking> rankingOfCompetition = repository.findByCompetition(competition.get());

            // comparaison
            Comparator<Ranking> rankingComparator = Comparator.comparingInt(Ranking::getScore).reversed();

            // tri
            rankingOfCompetition.sort(rankingComparator);

            // Update du champ rank
            for (int i = 0; i < rankingOfCompetition.size(); i++) {
                Ranking ranking = rankingOfCompetition.get(i);
                ranking.setRank(i + 1);
            }
            //updatedanslabase de donner
            for (Ranking ranking : rankingOfCompetition) {
                repository.save(ranking);
            }

            return rankingOfCompetition.stream().map(mapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new CompetitionException("Competition with code " + competition.get().getCode() + " dosent exists.");

        }    }

}
