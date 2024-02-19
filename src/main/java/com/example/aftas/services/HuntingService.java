package com.example.aftas.services;

import com.example.aftas.dto.Request.HuntingDtoRequest;
import com.example.aftas.dto.Response.HuntingDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.entity.Hunting;
import com.example.aftas.entity.Member;

import java.util.List;

public interface HuntingService {
    public Hunting addHunting(HuntingDtoRequest hunting);
    public List<Hunting> getAllHunting();
    public List<Hunting> getHuntingByCompetition(String competitionCode);
    public List<Hunting> getHuntingOfMember(String competition,String member);
}
