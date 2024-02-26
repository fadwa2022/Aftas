package com.example.aftas.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.aftas.entity.Permission.*;


@RequiredArgsConstructor
public enum Role {

    USER(Collections.EMPTY_SET),
    MANAGER(
    Set.of(
            MANAGER_READ,
            MANAGER_CREATE,
            MANAGER_UPDATE,
            MANAGER_DELETE,
            MEMBER_READ,
            MEMBER_CREATE,
            MEMBER_UPDATE,
            MEMBER_DELETE


    )
    )

    , MEMBER(
            Set.of(
                    MEMBER_READ,
                    MEMBER_CREATE,
                    MEMBER_UPDATE,
                    MEMBER_DELETE
            )

    )



    , JURY(
            Set.of(
                    JURY_READ,
                    JURY_CREATE,
                    JURY_UPDATE,
                    JURY_DELETE

            )
    ) ;

    @Getter
    private final Set<Permission> Permissions ;


    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions().stream().map(
                permission -> new SimpleGrantedAuthority(permission.getPermission())
        ).collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return authorities ;
    }


}