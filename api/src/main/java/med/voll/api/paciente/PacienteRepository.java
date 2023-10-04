package med.voll.api.paciente;

import med.voll.api.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT p.activo 
            from Paciente p 
            where p.id =: idPaciente
            """)
    Boolean findActivoById(Long idPaciente);
}
