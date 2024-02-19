package com.example.aftas.services.Impl;

import com.example.aftas.dto.FishDto;
import com.example.aftas.dto.Response.RankingDtoResponse;
import com.example.aftas.entity.Fish;
import com.example.aftas.entity.Level;
import com.example.aftas.entity.Ranking;
import com.example.aftas.mapper.Mapper;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service

public class FishServiceImpl implements FishService {
   @Autowired
    private FishRepository repository;
   @Autowired
   private LevelRepository levelRepository;
    @Autowired
    private Mapper<Fish, FishDto> mapper;
    @Override
    public List<FishDto> getAllFishs() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
   /* public Level addLevel(Level level){
        List<Level>levels =levelRepository.findAll();
        Level lastLevel = levels.get(levels.size() - 1);
        if (level.getPoints()>lastLevel.getPoints()){

        }
    }*/
}
