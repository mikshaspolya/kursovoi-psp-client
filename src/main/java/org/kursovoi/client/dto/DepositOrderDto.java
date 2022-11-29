package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositOrderDto {

    private long id;
    private String status;
    private long sum;
    private String dateOfIssue;
    private String dateOfEnd;
    private long idDeposit;
    private long idUser;
}
