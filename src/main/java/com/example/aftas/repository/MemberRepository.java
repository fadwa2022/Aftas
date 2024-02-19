package com.example.aftas.repository;

import com.example.aftas.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByIdentityNumber(String identityNumber);
}
