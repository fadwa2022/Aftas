package com.example.aftas.repository;


import com.example.aftas.entity.authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<authorities,Long> {
    authorities findByAuthoritieName(String authoririeName);
}
