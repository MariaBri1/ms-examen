package com.codigo.examen.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequest {
    private String nombres;
    private String apellidos;
    private String email;
    private String password;
}
