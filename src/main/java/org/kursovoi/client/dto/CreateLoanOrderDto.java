package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateLoanOrderDto {

    private long idUser;
    private long idLoan;
    private long sum;
}
