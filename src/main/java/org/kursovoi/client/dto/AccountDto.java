package org.kursovoi.client.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class AccountDto {

    private long id;
    private long sum;
    private String dateOfIssue;
    private String currency;
    private String status;
    private long holderId;
}
