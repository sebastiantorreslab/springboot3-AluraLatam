package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            SELECT m FROM medico m WHERE m.activo = 1 AND m.especialidad =:especialidad AND m.id not in(
            SELECT c.medico.id FROM consulta c 
            c.data =:fecha)
            ORDER BY rand()
            limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query("""
    SELECT m.activo FROM medico m WHERE m.id =: idMedico
    """)
    Boolean findActivoById(Long idMedico);
}
