package com.example.aftas.dto.Request;

import com.example.aftas.SuppEntity.CompetitionMembres;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompetitionMemberDtoRequest {
    private Long id;
    private String competitionCode;
    private String memberNum;
    public CompetitionMembres toModel(){
        Member membre1 = Member
                .builder()
                .username(memberNum)
                .build();
        Competition competition1= Competition.builder().code(competitionCode).build();
        return CompetitionMembres.
                builder()
                .member(membre1)
                .competition(competition1)
                .build();
    }
}
