package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Ranking;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RankingMapper implements Mapper<Ranking, RankingDtoResponse> {
    private final ModelMapper modelMapper;
    public RankingMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RankingDtoResponse mapToDto(Ranking entity) {
        return modelMapper.map(entity, RankingDtoResponse.class);    }

    @Override
    public Ranking mapToEntity(RankingDtoResponse dto) {
        return modelMapper.map(dto, Ranking.class);
    }
}
