package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.Response.HuntingDtoResponse;
import com.example.aftas.entity.Hunting;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HuntingMapper implements Mapper<Hunting, HuntingDtoResponse> {
    private final ModelMapper modelMapper;
    public HuntingMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HuntingDtoResponse mapToDto(Hunting entity) {
        return modelMapper.map(entity, HuntingDtoResponse.class);
    }

    @Override
    public Hunting mapToEntity(HuntingDtoResponse dto) {
        return modelMapper.map(dto, Hunting.class);
    }
}
