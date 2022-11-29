package org.kursovoi.client.dto;

import lombok.Data;

@Data
public class CreateCardDto {

    private String holderName;
    private String cardIssuer;
    private String type;
    private long idAccount;
}