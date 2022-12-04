package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyCourseDto implements Comparable<CurrencyCourseDto>{

    private long id;
    private String date;
    private double costUsd;
    private double costEur;
    private double costRub;

    @Override
    public int compareTo(CurrencyCourseDto o) {
        return LocalDate.parse(o.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)
                .compareTo(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
    }
}