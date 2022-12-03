package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanOrderDto {

    private long id;
    private String dateOfIssue;
    private String dateOfEnd;
    private long sum;
    private String status;
    private long idLoan;
    private long idUser;

    @Override
    public String toString() {
        return "Кредит: " +
                id +
                ", " + dateOfIssue +
                " - " + dateOfEnd +
                ", " + sum +
                ", " + status;
    }
}
