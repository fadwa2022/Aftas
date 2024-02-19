package com.example.aftas.services.Impl;
import com.example.aftas.dto.Request.CompetitionDtoRequest;
import com.example.aftas.dto.Response.CompetitionDtoResponse;
import com.example.aftas.entity.Competition;
import com.example.aftas.mapper.MaperRequest_Response;
import com.example.aftas.mapper.Mapper;
import com.example.aftas.repository.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class CompetitionTest {
    @Mock
    private CompetitionRepository repository;

    @Mock
    @Qualifier("competitionMapper")
    private Mapper<Competition, CompetitionDtoResponse> mapper;

    @Mock
    @Qualifier("competitionMapper")
    private MaperRequest_Response<CompetitionDtoRequest, CompetitionDtoResponse> maperRequestResponse;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCompetition() {
        CompetitionDtoRequest request = new CompetitionDtoRequest();
        CompetitionDtoResponse response = new CompetitionDtoResponse();
        Competition competition = new Competition();

        when(maperRequestResponse.mapRequestToResponse(request)).thenReturn(response);
        when(mapper.mapToEntity(response)).thenReturn(competition);
        when(repository.findByCode(response.getCode())).thenReturn(Optional.empty());
        when(repository.save(competition)).thenReturn(competition);
        when(mapper.mapToDto(competition)).thenReturn(response);

        CompetitionDtoResponse result = competitionService.createCompetition(request);

        assertNotNull(result);
        assertEquals(response, result);

        verify(repository, times(1)).findByCode(response.getCode());
        verify(repository, times(1)).save(competition);
        verify(mapper, times(1)).mapToDto(competition);
    }



}