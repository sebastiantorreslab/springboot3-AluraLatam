package med.voll.api.paciente;

import med.voll.api.dominio.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Long>{
    UserDetails findByLogin(String login);
}
