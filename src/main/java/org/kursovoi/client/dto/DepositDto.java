package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositDto {

    private long id;
    private double interest;
    private long monthToExpire;
    private String currency;
}