package org.kursovoi.client.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private Long sum;
    private String dateOfIssue;
    private String currency;
    private String status;
    private Long holderId;

    @Override
    public String toString() {
        return "Счет: " +
                id +
                ", " + dateOfIssue +
                ", " + currency +
                ", сумма = " + sum;
    }
}
