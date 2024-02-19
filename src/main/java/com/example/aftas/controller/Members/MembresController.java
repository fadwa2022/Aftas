package com.example.aftas.controller.Members;

import com.example.aftas.SuppEntity.CompetitionMembres;
import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Request.MemberDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Ranking;
import com.example.aftas.services.CompetitionService;
import com.example.aftas.services.MemberService;
import jakarta.validation.Valid;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/members")
public class MembresController {
    private MemberService memberService;
    @Autowired
    public MembresController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/{competitionCode}")
    public List<RankingDtoResponse> find(@PathVariable("competitionCode") final String competitionCode) {
      return memberService.getAllRankingByCompetition(competitionCode);
    }
    @PostMapping("/{competitionCode}")
    public ResponseEntity<Ranking> save(@Valid @RequestBody final MemberDtoRequest memberDtoRequest,@PathVariable("competitionCode") final String competitionCode) {
        System.out.println(memberDtoRequest);
        var savedDto = memberService.addMembertoRanking(memberDtoRequest,competitionCode);
        return (ResponseEntity<Ranking>) new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}
