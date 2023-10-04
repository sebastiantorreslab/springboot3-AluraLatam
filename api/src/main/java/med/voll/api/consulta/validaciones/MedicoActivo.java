package med.voll.api.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.DatosAgendarConsulta;
import med.voll.api.medico.MedicoRepository;
import org.springframework.stereotype.Component;


@Component
public class MedicoActivo implements ValidadorDeConsultas {


    private final MedicoRepository medicoRepository;

    public MedicoActivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }
        var medicoActivo= medicoRepository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidationException("El medico se encuentra inactivo en el sistema");
        }
    }
}
