package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.FishDto;
import com.example.aftas.entity.Fish;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FishMapper implements Mapper<Fish, FishDto> {
    private final ModelMapper modelMapper;
    public FishMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FishDto mapToDto(Fish entity) {
        return modelMapper.map(entity, FishDto.class);    }

    @Override
    public Fish mapToEntity(FishDto dto) {
        return modelMapper.map(dto, Fish.class);
    }
}
