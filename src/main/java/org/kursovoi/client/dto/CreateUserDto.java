package org.kursovoi.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {

    private String login;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
}