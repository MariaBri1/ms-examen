package com.codigo.examen.controller;

import com.codigo.examen.request.SignInRequest;
import com.codigo.examen.response.AutenticacionResponse;
import com.codigo.examen.service.AutenticacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutenticacionControllerTest {

    @Mock
    private AutenticacionService autenticacionService;

    @InjectMocks
    private AutenticacionController autenticacionController;

    @Test
    void testSignin_UsuarioValido_ReturnsOK() {
        String username = "testUser";
        String password = "testPassword";
        SignInRequest signInRequest = SignInRequest.builder().user(username).password(password).build();
        AutenticacionResponse expectedResponse = new AutenticacionResponse();
        expectedResponse.setToken("testToken");

        when(autenticacionService.signIn(signInRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AutenticacionResponse> response = autenticacionController.signin(signInRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(autenticacionService, times(1)).signIn(signInRequest);
    }
}
