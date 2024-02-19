package com.example.aftas.config;


import java.time.LocalDate;

public class CompetitionCodeGenerator {
    public static String generateCode(String location, LocalDate date) {
        if (location != null && date != null) {
            String locationPrefix = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
            return locationPrefix +

                    String.format("-%02d-%02d",  date.getDayOfMonth(),date.getMonthValue())+ "-"+date.getYear() % 100 ;
        } else {
            return null;
        }
    }
    }

