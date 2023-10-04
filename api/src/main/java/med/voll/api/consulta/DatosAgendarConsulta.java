package med.voll.api.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(Long id, @NotNull Long idPaciente, @NotNull Long idMedico, @NotNull @Future LocalDateTime fecha, Especialidad especialidad) {
}
