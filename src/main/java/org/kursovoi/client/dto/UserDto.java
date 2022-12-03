package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private long id;
    private String login;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private String status;
    private String email;
    private String role;

}
