package com.example.aftas.dto.Response;

import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Fish;
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

public class RankingDtoResponse {
    private Long id;
    private Integer rank;
    private Integer score;
    private Member member;
    private Competition competition;

}
