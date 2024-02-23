package com.example.aftas.services.Impl;

import com.example.aftas.dto.Request.MemberDtoRequest;
import com.example.aftas.dto.Response.MemberDtoResponse;
import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;
import com.example.aftas.exception.MembreException;
import com.example.aftas.mapper.Mapper;
import com.example.aftas.repository.*;
import com.example.aftas.services.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository repository;
    private RankingRepository rankingRepository;
    private CompetitionRepository competitionRepository;
    private AuthoritiesRepository authoritiesRepository;
    private Mapper<Member,MemberDtoResponse> mapper;
    private Mapper <Ranking, RankingDtoResponse> mapperranking;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(
            MemberRepository repository,
            @Qualifier("membreMapper") Mapper<Member,MemberDtoResponse> mapper,
            RankingRepository rankingRepository,
            CompetitionRepository competitionRepository,
            PasswordEncoder passwordEncoder,
            Mapper <Ranking, RankingDtoResponse> mapperranking,
            AuthoritiesRepository authoritiesRepository
    ){
        this.repository=repository;
        this.mapper=mapper;
        this.rankingRepository=rankingRepository;
        this.competitionRepository=competitionRepository;
       this.passwordEncoder = passwordEncoder;
        this.mapperranking=mapperranking;
        this.authoritiesRepository=authoritiesRepository;
    }


    @Override
    public Ranking addMembertoRanking(MemberDtoRequest memberDtoRequest, String competitionCode) {
        Ranking saverankingResponse = null;


        MemberDtoResponse memberDtoResponse = MemberDtoResponse.builder()
                .name(memberDtoRequest.getName())
                .familyName(memberDtoRequest.getFamilyName())
                .accessionDate(LocalDate.now())
                .nationality(memberDtoRequest.getNationality())
                .identityDocument(memberDtoRequest.getIdentityDocument())
                .identityNumber(memberDtoRequest.getIdentityNumber())
                .build();

        Competition competition = competitionRepository.findByCode(competitionCode).get();

        Member member = mapper.mapToEntity(memberDtoResponse);


        if (LocalDate.now().isBefore(competition.getDate().minusDays(1))) {
            Optional<Member> memberfind = repository.findByIdentityNumber(member.getIdentityNumber());
          //verifier si member est deja decklarer comme member ou non
            if (memberfind.isPresent()){
                Member existingMember = memberfind.get();
                boolean isMemberInCompetition = rankingRepository.existsByMembreAndCompetition(existingMember, competition);
                //pour tester si member is already in the competition
                if (isMemberInCompetition) {
                    throw new MembreException("Member with identity number "
                            + existingMember.getIdentityNumber() + " is already in the competition");
                }


                Ranking ranking = new Ranking();
                ranking.setCompetition(competition);
                ranking.setMembre(memberfind.get());
                saverankingResponse = rankingRepository.save(ranking);

            }else{
                member.setAccessionDate( LocalDate.now());
                Member savedMembre =addMember(member) ;

                Ranking ranking = new Ranking();
                ranking.setCompetition(competition);
                ranking.setMembre(savedMembre);

                saverankingResponse =  rankingRepository.save(ranking);

            }
        }else {
            throw new MembreException("The competition will commence in 24 hours, and during this period, we are unable to add any new members");
        }
        return saverankingResponse;

    }

    @Override
    public Member addMember(Member member) {
         member.setPassword(passwordEncoder.encode("1234"));
        return repository.save(member);
    }

    @Override
    public List<MemberDtoResponse> getAllMembers() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RankingDtoResponse> getAllRankingByCompetition(String competitionCode) {
       Competition competition = competitionRepository.findByCode(competitionCode).get();
        return rankingRepository.findByCompetition(competition)
                .stream()
                .map(mapperranking::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDtoResponse getMemberByNum() {
        return null;
    }

   /* @Override
    public void AddAuthoritiesToMember(String username, String authoritieName) {
        Member member= repository.findByUsername(username).get();
        authorities authorities =authoritiesRepository.findByAuthoritieName(authoritieName);
        member.getAuthorities().add(authorities);

    }*/

    @Override
    public Member LoadMemberByMemberUserName(String MemberUserName) {
        return repository.findByUsername(MemberUserName).get();
    }




}
