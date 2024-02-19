package com.example.aftas.dto;

import com.example.aftas.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FishDto {
    private Long id;
    private String name;
    private Double averageWeight;
    private Level level;

}
