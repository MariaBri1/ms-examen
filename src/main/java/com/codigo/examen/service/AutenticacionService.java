package com.codigo.examen.service;

import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AutenticacionResponse;

public interface AutenticacionService {

    AutenticacionResponse signIn(SignInRequest signInRequest);
}
