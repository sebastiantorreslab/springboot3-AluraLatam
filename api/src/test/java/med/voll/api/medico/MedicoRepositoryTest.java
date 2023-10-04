package med.voll.api.medico;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    private final MedicoRepository medicoRepository;
    private final TestEntityManager em;

    MedicoRepositoryTest(MedicoRepository medicoRepository, TestEntityManager em) {
        this.medicoRepository = medicoRepository;
        this.em = em;
    }


    @Test
    @DisplayName("deberia retornar nulo cuando el medico se cuentre en consulta con otro paciente en el mismo horario")
    void seleccionarMedicoConEspecialidadEnFecha() {

        var proximoLunes10H = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(10).withMinute(0);

//        var medico = registrarMedico();
//        var paciente= registrarPaciente();
//        registrarConsulta() ;

        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        assertThat(medicoLibre).isNull();




    }
}