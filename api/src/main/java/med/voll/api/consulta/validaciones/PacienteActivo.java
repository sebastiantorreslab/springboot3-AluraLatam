package med.voll.api.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.DatosAgendarConsulta;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo  implements ValidadorDeConsultas{

    private final PacienteRepository pacienteRepository;

    public PacienteActivo(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente() == null){
            return;
        }else{
            var pacienteActivo = pacienteRepository.findActivoById(datos.idPaciente());

            if(!pacienteActivo){
                throw new ValidationException("No se puede agendar cita si el paciente está inactivo");
            }
        }
    }
}
