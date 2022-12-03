package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyCourseDto {

    private long id;
    private String date;
    private double costUsd;
    private double costEur;
    private double costRub;
}