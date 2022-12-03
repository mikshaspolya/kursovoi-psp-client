package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositDto {

    private long id;
    private double interest;
    private long monthToExpire;
    private String currency;

    @Override
    public String toString() {
        return "Вид вклада: " +
                id +
                ", %" + interest +
                ", на " + monthToExpire + " месяцев" +
                ", " + currency;
    }
}