package med.voll.api.consulta.validaciones;
import jakarta.validation.ValidationException;

import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    private final ConsultaRepository consultaRepository;

    public PacienteSinConsulta( ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DatosAgendarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteConConsulta = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese día");
        }


    }
}
