package med.voll.api.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.paciente.UsuariosRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsuariosRepository usuariosRepository;

    public SecurityFilter(TokenService tokenService, UsuariosRepository usuariosRepository) {
        this.tokenService = tokenService;
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //obtener el token del header
        var token = request.getHeader("Authorization");
       // hay que hacer un handling al posible error que llege en esta petición
        if(token != null){
            token = token.replace("Bearer ","");
            String subject = tokenService.getSubject(token);
            if(subject != null){
               UserDetails usuario = usuariosRepository.findByLogin(subject);
               var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        filterChain.doFilter(request,response);

    }
}
