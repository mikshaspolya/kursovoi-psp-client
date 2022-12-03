package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {

    private long id;
    private String number;
    private String holderName;
    private String dateOfExpire;
    private String cvv;
    private String status;
    private String cardIssuer;
    private String type;

    @Override
    public String toString() {
        return "Карта: " +
                id +
                "," + holderName +
                "," + dateOfExpire +
                ", " + status +
                ", " + cardIssuer +
                ", " + type;
    }
}