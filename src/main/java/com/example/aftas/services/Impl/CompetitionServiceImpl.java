package com.example.aftas.services.Impl;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.exception.CompetitionException;
import com.example.aftas.mapper.MaperRequest_Response;
import com.example.aftas.mapper.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private CompetitionRepository repository;
    private Mapper<Competition, CompetitionDtoResponse> mapper;
    private MaperRequest_Response<CompetitionDtoRequest,CompetitionDtoResponse> maperRequestResponse;

    @Autowired
    public CompetitionServiceImpl(
            CompetitionRepository repository,
            @Qualifier("competitionMapper") Mapper<Competition, CompetitionDtoResponse> mapper,
            @Qualifier("competitionMapper") MaperRequest_Response<CompetitionDtoRequest,CompetitionDtoResponse> maperRequestResponse) {
        this.repository = repository;
        this.mapper = mapper;
        this.maperRequestResponse=maperRequestResponse;
    }
    @Override
    public CompetitionDtoResponse createCompetition(CompetitionDtoRequest competitionRequest) {

         competitionRequest.generateCode();

         CompetitionDtoResponse competitionResponse= maperRequestResponse.mapRequestToResponse(competitionRequest);

         Competition competition =mapper.mapToEntity(competitionResponse);

         if (repository.findByCode(competitionResponse.getCode()).isPresent()) {

             throw new CompetitionException("Competition with code " + competitionResponse.getCode() + " already exists.");

         } else {
             Competition savedCompetition = repository.save(competition);

             CompetitionDtoResponse savedCompetitionResponse = mapper.mapToDto(savedCompetition);

             return savedCompetitionResponse;

         }

}

    @Override
    public CompetitionDtoResponse updateCompetition(CompetitionDtoRequest competitionRequest) {
        try {
            String competitionCode = competitionRequest.getCode();
            Optional<Competition> existingCompetitionOptional= repository.findByCode(competitionCode);

            if (existingCompetitionOptional.isPresent()) {
                if (competitionCode == null || competitionCode.isEmpty()) {
                    competitionRequest.generateCode();
                }

                CompetitionDtoResponse competitionResponse = maperRequestResponse.mapRequestToResponse(competitionRequest);

                Competition updatedCompetition = mapper.mapToEntity(competitionResponse);

                existingCompetitionOptional.ifPresent(existingCompetition -> {
                   existingCompetition.setDate(updatedCompetition.getDate());
                    existingCompetition.setStartTime(updatedCompetition.getStartTime());
                    existingCompetition.setEndTime(updatedCompetition.getEndTime());
                    existingCompetition.setNumberOfParticipants(updatedCompetition.getNumberOfParticipants());
                    existingCompetition.setLocation(updatedCompetition.getLocation());
                    existingCompetition.setAmount(updatedCompetition.getAmount());

                    repository.save(existingCompetition);
                });

                CompetitionDtoResponse updatedCompetitionResponse = mapper.mapToDto(updatedCompetition);

                return updatedCompetitionResponse;
            } else {
                throw new CompetitionException("Competition with code " + competitionCode + " not found.");
            }
        } catch (Exception e) {

            throw new CompetitionException("Error updating competition");
        }    }

    @Override
    public List<CompetitionDtoResponse> getAllCompetition() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
    public Page<CompetitionDtoResponse> getAllPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::mapToDto);
    }

    @Override
    public CompetitionDtoResponse getCompititionByCode(String code) {
        return null;
    }

    @Override
    public CompetitionDtoResponse getCompititionByDateAndLocation(LocalDate localDate, String location) {
        return null;
    }
}
