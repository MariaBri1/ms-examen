package com.codigo.examen.service.impl;

import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.service.AutenticacionService;
import com.codigo.examen.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AutenticacionResponse;

@Service
@RequiredArgsConstructor
public class AutenticacionServiceImpl implements AutenticacionService{
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager ;
    private final JWTService jwtService;


    @Override
    public AutenticacionResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUser(),signInRequest.getPassword()));
        var user = usuarioRepository.findByUsername(signInRequest.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Email no valido"));

        var jwt = jwtService.generateToken(user);
        AutenticacionResponse authenticationResponse =  new AutenticacionResponse();
        authenticationResponse.setToken(jwt);
        return authenticationResponse;
    }
}
