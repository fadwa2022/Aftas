package com.example.aftas.services;

import com.example.aftas.SuppEntity.CompetitionMembres;
import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Request.MemberDtoRequest;
import com.example.aftas.dto.Response.MemberDtoResponse;
import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;

import java.util.List;

public interface MemberService {
    public Ranking addMembertoRanking(MemberDtoRequest memberDtoRequest, String competitionCode);
    public Member addMember(Member member);

    public List<MemberDtoResponse> getAllMembers();
    public  List<RankingDtoResponse> getAllRankingByCompetition(String competitionCode);
    public MemberDtoResponse getMemberByNum();
}
