package com.codigo.examen.service.imp;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
import com.codigo.examen.service.impl.UsuarioServiceImpl;
import com.codigo.examen.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RolRepository rolRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        usuarioService = new UsuarioServiceImpl(usuarioRepository, rolRepository);
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

        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(rolRepository.findById(any())).thenReturn(Optional.of(rol));
        when(usuarioRepository.save(any())).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioService.createUsuario(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("alberth2", response.getBody().getUsername());
    }

    @Test
    @DisplayName("Test cuando ya existe el usuario")
    void createUsuarioExist() {
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

        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioService.createUsuario(usuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Test para obtener un usuario por su Id")
    void getUsuarioById_cuandoIdUsuarioExiste_returnsUsuario() {


    }

    @Test
    @DisplayName("Test en caso no exista Id del usuario")
    void getUsuarioById_cuandoIdUsuarioNoExiste_ReturnsNotFound() {

    }

    @Test
    @DisplayName("Test para asignar roles")
    void obtenerUsuario_AsignaRolYRespuesta_returnsResponseEntity() {

    }

    @Test
    @DisplayName("Test en caso no asigna role y no hay respuesta")
    void obtenerUsuario_noAsignaRolYRespuesta_returnsResponseEntity() {

    }

    @Test
    void actualizarOut() {
    }

    @Test
    void deleteOut() {
    }
}
