package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SelectUserResponse {

    private final List<SelectUserDto> data;
    private final int totalPages;

}
