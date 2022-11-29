package org.kursovoi.client.dto;

import lombok.Data;

@Data
public class TransactionDto {

    private long idTo;
    private long idFrom;
    private long sum;
}
