package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class SelectUserResponse {

    private final List<SelectUserDto> UserList;
    private final int totalPages;

}
