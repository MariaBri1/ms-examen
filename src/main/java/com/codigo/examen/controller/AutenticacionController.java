package com.codigo.examen.controller;

import com.codigo.examen.service.AutenticacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AutenticacionResponse;

@RestController
@RequestMapping("/api/v1/autenticacion")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    @PostMapping("/signin")
    public ResponseEntity<AutenticacionResponse> signin(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(autenticacionService.signIn(signInRequest));
    }
}
