package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDto {

    private long id;
    private double interest;
    private long monthsToReturn;
    private String currency;

    @Override
    public String toString() {
        return "Вид кредита: " +
                id +
                ", %" + interest +
                ", возврат через " + monthsToReturn + " месяцев" +
                ", " + currency;
    }
}
