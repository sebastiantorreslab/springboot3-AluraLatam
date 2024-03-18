package med.voll.api.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.dominio.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.config.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario) {


        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("voll")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExp())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
            // Invalid Signing configuration / Couldn't convert Claims.
        }

    }

    private Instant generarFechaExp() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-5:00"));
    }


    public String getSubject(String token) {
        if(token == null ){
            throw new RuntimeException("token no valido");
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("voll")
                    // reusable verifier instance
                    .build()
                    .verify(token);

            if(verifier == null){
                throw new RuntimeException("invalid verifier");
            }

            return verifier.getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error verificando el JWT: " + exception.getMessage());

        }


    }

}
