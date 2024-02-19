package com.example.aftas.services;

import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Ranking;

import java.util.List;

public interface RankingService {
    public List<Ranking> calculeScors(String compititionCode);
    public List<RankingDtoResponse> calculeRanking (String compititionCode);
}
