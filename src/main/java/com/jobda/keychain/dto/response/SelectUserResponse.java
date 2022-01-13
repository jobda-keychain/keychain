package com.jobda.keychain.dto.response;

import com.jobda.keychain.entity.platform.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class SelectUserResponse {

    private final List<SelectUserDto> UserList;
    private final int totalPages;


    @Getter
    @AllArgsConstructor
    public static class SelectUserDto{
        private Long id;
        private String userId;
        private String password;
        private ServiceType platform;
        private String environment;
        private String description;

       private SelectUserDto(){

        }
    }
}
