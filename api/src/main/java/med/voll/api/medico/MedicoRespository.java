package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRespository extends JpaRepository<Medico, Long> {
}
