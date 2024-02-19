package com.example.aftas.mapper.Impl;

import com.example.aftas.dto.Response.MemberDtoResponse;
import com.example.aftas.entity.Member;
import com.example.aftas.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("membreMapper")

public class MembreMapper implements Mapper <Member, MemberDtoResponse>{
    private final ModelMapper modelMapper;
    public MembreMapper(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MemberDtoResponse mapToDto(Member entity) {
        return modelMapper.map(entity, MemberDtoResponse.class);    }

    @Override
    public Member mapToEntity(MemberDtoResponse dto) {
        return modelMapper.map(dto, Member.class);
    }
}
