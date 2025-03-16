package com.bookhaven.auth_service.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FormRegisterUser{
    private String username;
    private boolean enable;
    private String firstName;
    private String lastName;
    private String email;
    private Credentals credentals;
    private List<String> realmRoles;
}
