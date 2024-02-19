package com.example.aftas.dto.Request;

import com.example.aftas.config.CompetitionCodeGenerator;
import com.example.aftas.entity.Competition;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CompetitionDtoRequest {
    private String code;
    @NotNull(message = "")
    private LocalDate date;
    @JsonFormat(pattern = "HH:mm:ss")

    private Time startTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time endTime;
    @NotNull
    private Integer numberOfParticipants;
    @NotNull
    private String location;
    private Double amount;
    public void generateCode() {
        this.code = CompetitionCodeGenerator.generateCode(location, date);
    }

}
