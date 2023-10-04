package med.voll.api.consulta;

import med.voll.api.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    private final List<ValidadorDeConsultas> validadores;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, MedicoRepository medicoRepository, List<ValidadorDeConsultas> validadores) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.validadores = validadores;
    }

    public void agendar(DatosAgendarConsulta datos){

        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDerIntegridad("este id de paciente no fué encontrado");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDerIntegridad("este id de médico no fué encontrado");
        }

        //validaciones implementando un desing patter

        validadores.forEach(v->v.validar(datos)); // se cumplen los principios solid como pilar de polimorfismo

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var medico = seleccionarMedico(datos);
        var consulta = new Consulta(null,medico, paciente, datos.fecha());
            consultaRepository.save( consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico() !=null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null){
            throw new ValidacionDerIntegridad("debe seleccionarse una especialidad para el médico");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha( datos.especialidad(),datos.fecha());
    }

}
