package com.jobda.keychain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class SelectAccountResponse {

    private final List<SelectAccountDto> data;
    private final int totalPages;

}
