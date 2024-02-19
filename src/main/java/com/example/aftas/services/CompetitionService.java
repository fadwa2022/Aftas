package com.example.aftas.services;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface CompetitionService {
     public CompetitionDtoResponse createCompetition(CompetitionDtoRequest competitionRequest);
     public CompetitionDtoResponse updateCompetition(CompetitionDtoRequest competitionRequest);
     public List<CompetitionDtoResponse> getAllCompetition();
     public Page<CompetitionDtoResponse> getAllPagination(int page, int size) ;

          public CompetitionDtoResponse getCompititionByCode(String code);
     public CompetitionDtoResponse getCompititionByDateAndLocation(LocalDate localDate ,String location);
}
