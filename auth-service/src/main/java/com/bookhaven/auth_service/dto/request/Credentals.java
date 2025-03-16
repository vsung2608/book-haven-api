package com.bookhaven.auth_service.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credentals {
    private String type;
    private String value;
    private boolean temporary;
}
