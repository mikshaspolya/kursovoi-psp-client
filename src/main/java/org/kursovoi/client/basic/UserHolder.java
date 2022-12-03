package org.kursovoi.client.basic;

import org.kursovoi.client.dto.UserDto;

public class UserHolder {

    private static UserDto user;

    public static UserDto getUser() {
        return user;
    }

    public static void setUser(UserDto user) {
        UserHolder.user = user;
    }
}
