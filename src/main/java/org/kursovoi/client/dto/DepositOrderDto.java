package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositOrderDto {

    private long id;
    private String status;
    private long sum;
    private String dateOfIssue;
    private String dateOfEnd;
    private long idDeposit;
    private long idUser;

    @Override
    public String toString() {
        return "Вклад: " +
                id +
                ", " + status +
                ", сумма=" + sum +
                ", " + dateOfIssue +
                " - " + dateOfEnd;
    }
}
