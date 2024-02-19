package com.example.aftas.controller.Hunting;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Request.HuntingDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.dto.Response.HuntingDtoResponse;
import com.example.aftas.entity.Hunting;
import com.example.aftas.services.HuntingService;
import jakarta.validation.Valid;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hunting")
@CrossOrigin(origins = "*")
public class HuntingController {
    private HuntingService huntingService;
    public  HuntingController ( HuntingService huntingService){
        this.huntingService=huntingService;
    }
    @GetMapping
    public List<Hunting> getAll(){
        return this.huntingService.getAllHunting();
    }
    @GetMapping("/huntingOfCompetition/{competitionCode}")
    public List<Hunting> getHuntingOfCompetition(@PathVariable("competitionCode") final String competitionCode){
        return this.huntingService.getHuntingByCompetition(competitionCode);
    }
    @GetMapping("/huntingOfMember/{competitionCode}/{memberIdentityNumber}")
    public List<Hunting> getHuntingOfMembre(@PathVariable("competitionCode") final String competitionCode,@PathVariable("memberIdentityNumber") final String memberIdentityNumber){
        return this.huntingService.getHuntingOfMember(competitionCode,memberIdentityNumber);
    }
    @PostMapping
    public ResponseEntity<Hunting> save(@Valid @RequestBody  HuntingDtoRequest request) {

 var savedDto = huntingService.addHunting(request);

      return (ResponseEntity<Hunting>) new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
}
