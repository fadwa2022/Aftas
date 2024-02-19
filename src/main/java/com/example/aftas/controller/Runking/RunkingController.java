package com.example.aftas.controller.Runking;

import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Ranking;
import com.example.aftas.services.MemberService;
import com.example.aftas.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/ranking")
public class RunkingController {
    private RankingService rankingService;
    @Autowired
    public RunkingController( RankingService rankingService) {
        this.rankingService =rankingService;
    }
    @GetMapping("calculeScore/{competitionCode}")
    public List<Ranking> calculeScores(@PathVariable("competitionCode") final String competitionCode) {
        return rankingService.calculeScors(competitionCode);
    }
    @GetMapping("calculeRank/{competitionCode}")
    public List<RankingDtoResponse> calculeRanking(@PathVariable("competitionCode") final String competitionCode) {
        return rankingService.calculeRanking(competitionCode);
    }
}
