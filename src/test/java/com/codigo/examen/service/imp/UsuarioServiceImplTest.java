package com.codigo.examen.service.imp;

import com.codigo.examen.entity.Rol;
import com.codigo.examen.entity.Usuario;
import com.codigo.examen.repository.RolRepository;
import com.codigo.examen.repository.UsuarioRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioService.getUsuarioById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("alberth2", response.getBody().getUsername());
    }

    @Test
    @DisplayName("Test para obtener un usuario por su Id exitosamente")
    void getUsuarioByIdNotFound() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioService.getUsuarioById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
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

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuario));
        when(rolRepository.findById(any())).thenReturn(Optional.of(rol));
        when(usuarioRepository.save(any())).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("alberth2", response.getBody().getUsername());
    }

    @Test
    @DisplayName("Test para actualizar un usuario no existente")
    void updateUsuarioNotFound() {
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

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("Test para actualizar un usuario con data mal ingresada")
    void updateUsuarioBadRequest() {
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

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
        when(usuarioRepository.findByUsername(anyString())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioService.updateUsuario(1L, usuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
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

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioService.deleteUsuario(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioRepository, times(1)).delete(usuario);
    }

    @Test
    @DisplayName("Test para eliminar un usuario que no existe")
    void deleteUsuarioNotFound() {
        Usuario usuario = Usuario.builder().build();

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioService.deleteUsuario(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioRepository, times(0)).delete(usuario);
    }
}
