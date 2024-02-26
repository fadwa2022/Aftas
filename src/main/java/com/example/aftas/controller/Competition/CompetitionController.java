package com.example.aftas.controller.Competition;

import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.services.CompetitionService;
import jakarta.validation.Valid;
import org.hibernate.boot.model.naming.Identifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compitition")
@CrossOrigin
public class CompetitionController {
    private CompetitionService competitionService;
    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @GetMapping
    public List<CompetitionDtoResponse> getAll(){

        System.out.println("adwa");
        return this.competitionService.getAllCompetition();
    }

    @GetMapping("/{page}/{size}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_MEMBER') or hasAuthority('SCOPE_ROLE_JURY')")
    public Page<CompetitionDtoResponse> getAllEntities(
            @PathVariable("page") int page,
            @PathVariable("size") int size) {
        return competitionService.getAllPagination(page, size);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_MANAGER') or hasAuthority('SCOPE_ROLE_JURY')")
    public ResponseEntity<CompetitionDtoResponse> save(@Valid @RequestBody final CompetitionDtoRequest request) {
        var savedDto = competitionService.createCompetition(request);
        return (ResponseEntity<CompetitionDtoResponse>) new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }
    @PutMapping("/{code}")
    public ResponseEntity<CompetitionDtoResponse> update(@PathVariable("code") final Identifier code, @Valid @RequestBody final CompetitionDtoRequest competitionDtoRequest) {
        CompetitionDtoResponse competitionDtoResponse = competitionService.updateCompetition(competitionDtoRequest);
        return new ResponseEntity<>(competitionDtoResponse, HttpStatus.OK);
    }

}
