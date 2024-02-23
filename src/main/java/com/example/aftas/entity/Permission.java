package com.example.aftas.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    MEMBER_READ("member::read"),
    MEMBER_UPDATE("member::update"),
    MEMBER_DELETE("member::delete"),
    MEMBER_CREATE("member::create"),


    MANAGER_READ("manager::read"),
    MANAGER_UPDATE("manager::update"),
    MANAGER_DELETE("manager::delete"),
    MANAGER_CREATE("manager::create"),

    JURY_READ("jury::read"),
    JURY_UPDATE("jury::update"),
    JURY_DELETE("jury::delete"),
    JURY_CREATE("jury::create"),









    ;





    @Getter
    private final String permission ;






}