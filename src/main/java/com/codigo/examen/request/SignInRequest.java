package com.codigo.examen.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInRequest {
    private String user;
    private String password;
}
