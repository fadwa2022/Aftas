package com.example.aftas.entity;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long num;
    private String username;
    private String name;
    private String familyName;
    private String password;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Hunting> huntings;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<authorities> authorities =new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = role.getAuthorities().stream().map(
                permission -> new SimpleGrantedAuthority(permission.getAuthority())
        ).collect(Collectors.toList());

       // authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name));
        return authorities ;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
