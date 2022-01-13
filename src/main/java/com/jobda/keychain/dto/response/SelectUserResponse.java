package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class SelectUserResponse {

    private List<SelectUserDto> UserList;

    @Getter
    public static class SelectUserDto{
        private Long id;
        private String userId;
        private String password;
        private ServiceType platform;
        private String environment;
        private String description;
    }
}
