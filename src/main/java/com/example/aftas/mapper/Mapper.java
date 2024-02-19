package com.example.aftas.mapper;

public interface Mapper<Entity, Dto> {

    Dto mapToDto(final Entity a);
    Entity mapToEntity(final Dto b);

}