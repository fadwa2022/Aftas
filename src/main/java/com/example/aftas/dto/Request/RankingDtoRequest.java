package com.example.aftas.dto.Request;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Member;
import com.example.aftas.entity.Ranking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RankingDtoRequest {
    private Long id;
    private Integer rank;
    private Integer score;
    private String member_id;
    private String competition_id;
    public Ranking toModel(){
        Member membre1 = Member
                .builder()
                .username(member_id)
                .build();
        Competition competition1= Competition.builder().code(competition_id).build();
        return Ranking.
                builder()
                .membre(membre1)
                .competition(competition1)
                .build();
    }
}
