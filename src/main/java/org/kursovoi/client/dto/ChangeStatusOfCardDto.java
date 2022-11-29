package org.kursovoi.client.dto;

import lombok.Data;

@Data
public class ChangeStatusOfCardDto {

    private long cardId;
    private String newStatus;
}