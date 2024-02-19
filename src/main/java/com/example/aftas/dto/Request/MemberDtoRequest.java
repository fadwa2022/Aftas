package com.example.aftas.dto.Request;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MemberDtoRequest {

    @NotNull
    private String name;

    @NotNull
    private String familyName;

    private LocalDate accessionDate;

    @NotNull
    private String nationality;

    @NotNull
    private IdentityDocumentType identityDocument;

    @NotNull
    private String identityNumber;

}
