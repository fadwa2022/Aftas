package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.LevelDto;
import com.example.aftas.entity.Level;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LevelMapper implements Mapper<Level, LevelDto> {
    private final ModelMapper modelMapper;
    public LevelMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LevelDto mapToDto(Level entity) {
        return modelMapper.map(entity, LevelDto.class);    }

    @Override
    public Level mapToEntity(LevelDto dto) {
        return modelMapper.map(dto, Level.class);
    }
}
