package med.voll.api.consulta;

import jakarta.validation.ValidationException;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRespository;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final PacienteRepository pacienteRepository;

    private final MedicoRespository medicoRespository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRespository medicoRespository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRespository = medicoRespository;
    }

    public void agendar(DatosAgendarConsulta datos){

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDerIntegridad("este id de paciente no fué encontrado");
        }

        if(datos.idMedico() != null && medicoRespository.existsById(datos.idMedico())){
            throw new ValidacionDerIntegridad("este id de médico no fué encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        var consulta = new Consulta(null,medico, paciente, datos.fecha());
            consultaRepository.save( consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico() !=null){
            return medicoRespository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionDerIntegridad("debe seleccionarse una especialidad para el médico");
        }
        return medicoRespository.seleccionarMedicoConEspecialidadEnFecha( datos.especialidad(),datos.fecha());
    }

}
