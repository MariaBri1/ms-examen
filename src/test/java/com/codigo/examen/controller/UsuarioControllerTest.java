package com.codigo.examen.controller;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.service.UsuarioService;
import com.codigo.examen.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;
    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioController = new UsuarioController(usuarioService);
    }

    @Test
    @DisplayName("Test para crear un usuario exitosamente")
    void createUsuarioSuccess() {
        Rol rol = Rol.builder()
                .idRol(1L)
                .nombreRol("ADMIN")
                .build();

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .username("alberth2")
                .password("123")
                .email("alberth2@gmail.com")
                .telefono("929907631")
                .roles(Set.of(rol))
                .enabled(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .accountnonexpire(true)
                .build();

        when(usuarioService.createUsuario(usuario)).thenReturn(ResponseEntity.ok(usuario));

        ResponseEntity<Usuario> response = usuarioController.createUsuario(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(usuarioService, times(1)).createUsuario(usuario);
    }

    @Test
    @DisplayName("Test para obtener un usuario por su Id exitosamente")
    void getUsuarioByIdSuccess() {
        Rol rol = Rol.builder()
                .idRol(1L)
                .nombreRol("ADMIN")
                .build();

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .username("alberth2")
                .password("123")
                .email("alberth2@gmail.com")
                .telefono("929907631")
                .roles(Set.of(rol))
                .enabled(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .accountnonexpire(true)
                .build();

        when(usuarioService.getUsuarioById(anyLong())).thenReturn(ResponseEntity.ok(usuario));

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(usuarioService, times(1)).getUsuarioById(1L);
    }

    @Test
    @DisplayName("Test para actualizar un usuario exitosamente")
    void updateUsuarioSuccess() {
        Rol rol = Rol.builder()
                .idRol(1L)
                .nombreRol("ADMIN")
                .build();

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .username("alberth2")
                .password("123")
                .email("alberth2@gmail.com")
                .telefono("929907631")
                .roles(Set.of(rol))
                .enabled(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .accountnonexpire(true)
                .build();

        when(usuarioService.updateUsuario(anyLong(), any())).thenReturn(ResponseEntity.ok(usuario));

        ResponseEntity<Usuario> response = usuarioController.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
        verify(usuarioService, times(1)).updateUsuario(1L, usuario);
    }

    @Test
    @DisplayName("Test para eliminar un usuario por su Id exitosamente")
    void deleteUsuarioSuccess() {
        Rol rol = Rol.builder()
                .idRol(1L)
                .nombreRol("ADMIN")
                .build();

        Usuario usuario = Usuario.builder()
                .idUsuario(1L)
                .username("alberth2")
                .password("123")
                .email("alberth2@gmail.com")
                .telefono("929907631")
                .roles(Set.of(rol))
                .enabled(true)
                .accountnonlocked(true)
                .credentialsnonexpired(true)
                .accountnonexpire(true)
                .build();

        when(usuarioService.deleteUsuario(anyLong())).thenReturn(ResponseEntity.noContent().build());

        ResponseEntity<?> response = usuarioController.deleteUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(usuarioService, times(1)).deleteUsuario(1L);
    }

}
