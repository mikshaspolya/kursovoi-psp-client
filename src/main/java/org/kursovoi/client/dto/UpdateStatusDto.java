package org.kursovoi.client.dto;

import lombok.Data;

@Data
public class UpdateStatusDto {

    private String newStatus;
    private long id;

}
