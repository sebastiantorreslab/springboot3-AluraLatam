package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.config.security.DatosJWTtoken;
import med.voll.api.config.security.TokenService;
import med.voll.api.dominio.usuarios.DatosAuthUsuario;
import med.voll.api.dominio.usuarios.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> authUser(@RequestBody @Valid DatosAuthUsuario datosAuthUsuario){
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAuthUsuario.username(), datosAuthUsuario.clave());
        authenticationManager.authenticate(authenticationToken);
        Authentication usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}
