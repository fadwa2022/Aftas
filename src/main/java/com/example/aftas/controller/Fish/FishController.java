package com.example.aftas.controller.Fish;

import com.example.aftas.dto.FishDto;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.services.CompetitionService;
import com.example.aftas.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fish")
@CrossOrigin

public class FishController {
    private FishService fishService;
    @Autowired
    public FishController(FishService fishService) {
        this.fishService = fishService;
    }
    @GetMapping
    public List<FishDto> getAll(){
        return this.fishService.getAllFishs();
    }
}
