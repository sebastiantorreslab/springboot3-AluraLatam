package med.voll.api.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.ConsultaRepository;
import med.voll.api.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;


@Component
public class MedicoConConsulta implements ValidadorDeConsultas {

    private final ConsultaRepository consultaRepository;

    public MedicoConConsulta(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public void validar(DatosAgendarConsulta datos){

        if(datos.idMedico() == null){
            return;
        }

        var medicoConConsulta = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(),datos.fecha());

        if(medicoConConsulta){
            throw new ValidationException("Este medico ya tiene una consulta en este horario");
        }






    }
}
