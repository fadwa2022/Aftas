package com.example.aftas.repository;

import com.example.aftas.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {
    Optional<Competition> findByCode(String code);

}
