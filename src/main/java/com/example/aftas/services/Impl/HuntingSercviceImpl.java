package com.example.aftas.services.Impl;

import com.example.aftas.dto.Request.HuntingDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.dto.Response.HuntingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Hunting;
import com.example.aftas.entity.Member;
import com.example.aftas.exception.CompetitionException;
import com.example.aftas.exception.HuntingException;
import com.example.aftas.exception.MembreException;
import com.example.aftas.mapper.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.services.HuntingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingSercviceImpl implements HuntingService {
    private HuntingRepository huntingRepository;
    private CompetitionRepository competitionRepository;
    private MemberRepository memberRepository;
    private Mapper<Hunting, HuntingDtoResponse> mapper;
    @Autowired
    public HuntingSercviceImpl (
            HuntingRepository huntingRepository,
            CompetitionRepository competitionRepository,
            Mapper<Hunting,HuntingDtoResponse> mapper,
            MemberRepository memberRepository
    ){
        this.huntingRepository=huntingRepository;
        this.mapper=mapper;
        this.competitionRepository=competitionRepository;
        this.memberRepository=memberRepository;
    }
    @Override
    public Hunting addHunting(HuntingDtoRequest huntingDtoRequest) {
        Hunting hunting =huntingDtoRequest.toModel();
        Hunting huntingAlredyExist=huntingRepository.findHuntingByCompetitionAndMembreAndFish(hunting.getCompetition(),hunting.getMembre(),hunting.getFish());
        if (huntingAlredyExist !=null){
            huntingAlredyExist.setNumberOfFish(huntingAlredyExist.getNumberOfFish()+hunting.getNumberOfFish());
            Hunting huntingupdate = huntingRepository.save(huntingAlredyExist);
            return huntingupdate;

        } else {
            Hunting huntingsaved = huntingRepository.save(hunting);

            return huntingsaved;

        }
    }

    @Override
    public List<Hunting> getAllHunting() {
       List<Hunting> huntings=huntingRepository.findAll();
        System.out.println(huntings);

        return huntings;
    }

    @Override
    public List<Hunting> getHuntingByCompetition(String competitionCode) {
        Competition competition = competitionRepository.findByCode(competitionCode).get();
        return huntingRepository.findByCompetition(competition);
    }

    @Override
    public List<Hunting> getHuntingOfMember(String competitionCode, String memberIdentityNumber) {
        Competition competition =competitionRepository.findByCode(competitionCode).orElseThrow(() ->
                new CompetitionException("Competition with code " + competitionCode + " not found"));
        Member member = memberRepository.findByIdentityNumber(memberIdentityNumber).orElseThrow(() ->
                new MembreException("Member with identity number " + memberIdentityNumber + " not found"));
        List<Hunting> hunting = huntingRepository.findHuntingByCompetitionAndMembre(competition, member);



        if (hunting == null) {
            throw new HuntingException("Hunting record not found for the given competition and member");
        }

        return hunting ;
    }

}
