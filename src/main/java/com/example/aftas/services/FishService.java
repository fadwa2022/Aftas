package com.example.aftas.services;

import com.example.aftas.dto.FishDto;
import com.example.aftas.entity.Fish;
import org.springframework.stereotype.Service;

import java.util.List;
public interface FishService {
    public List<FishDto> getAllFishs();
}
