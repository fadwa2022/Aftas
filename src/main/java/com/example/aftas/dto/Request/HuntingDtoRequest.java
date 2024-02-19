package com.example.aftas.dto.Request;

import com.example.aftas.entity.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HuntingDtoRequest {

    private Integer numberOfFish;
    private String competition;
    private Long member;
    private Long fish_id;

    public Hunting toModel(){

        Competition competition1= Competition.builder().code(competition).build();
        Fish fish1= Fish.builder().id(fish_id).build();
        Member member1=Member.builder().num(member).build();
        return Hunting.
                builder()
                .numberOfFish(numberOfFish)
                .competition(competition1)
                .fish(fish1)
                .membre(member1)
                .build();
    }
}
