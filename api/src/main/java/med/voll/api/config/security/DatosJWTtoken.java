package med.voll.api.config.security;

public record DatosJWTtoken(String id,
                            String username,
                            String clave,
                            String jwtToken) {
}
