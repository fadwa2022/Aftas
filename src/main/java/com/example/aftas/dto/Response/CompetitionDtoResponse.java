package com.example.aftas.dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDtoResponse {

    private String code;

    private LocalDate date;

    private Time startTime;

    private Time endTime;

    private Integer numberOfParticipants;

    private String location;

    private Double amount;
}
