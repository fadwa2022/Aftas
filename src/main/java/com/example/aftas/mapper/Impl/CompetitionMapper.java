package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.mapper.MaperRequest_Response;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component("competitionMapper")
public class CompetitionMapper implements Mapper<Competition, CompetitionDtoResponse>, MaperRequest_Response<CompetitionDtoRequest,CompetitionDtoResponse> {
    private final ModelMapper modelMapper;
    public CompetitionMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CompetitionDtoResponse mapToDto(Competition entity) {
        return modelMapper.map(entity, CompetitionDtoResponse.class);
    }


    @Override
    public Competition mapToEntity(CompetitionDtoResponse dto) {
        return modelMapper.map(dto, Competition.class);
    }

    @Override
    public CompetitionDtoResponse mapRequestToResponse(CompetitionDtoRequest competitionDtoRequest) {
        return modelMapper.map(competitionDtoRequest, CompetitionDtoResponse.class);
    }

    @Override
    public CompetitionDtoRequest mapResponseToRequest(CompetitionDtoResponse competitionDtoResponse) {
        return modelMapper.map(competitionDtoResponse, CompetitionDtoRequest.class);
    }
}
