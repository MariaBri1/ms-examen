package com.codigo.examen.service.imp;

import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AutenticacionResponse;
import com.codigo.examen.service.JWTService;
import com.codigo.examen.service.impl.AutenticacionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacionServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @InjectMocks
    private AutenticacionServiceImpl autenticacionService;

    @Test
    void testSignIn_UsuarioValido_GeneraToken() {
        String username = "testUser";
        String password = "testPassword";
        SignInRequest signInRequest = SignInRequest.builder().user(username).password(password).build();

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);

        String token = "testToken";

        when(usuarioRepository.findByUsername(username)).thenReturn(Optional.of(usuario));
        when(jwtService.generateToken(usuario)).thenReturn(token);

        AutenticacionResponse response = autenticacionService.signIn(signInRequest);

        assertEquals(token, response.getToken());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(usuarioRepository, times(1)).findByUsername(username);
        verify(jwtService, times(1)).generateToken(usuario);
    }

}
