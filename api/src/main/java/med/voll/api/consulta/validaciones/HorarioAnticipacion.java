package med.voll.api.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class HorarioAnticipacion  implements ValidadorDeConsultas{

    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        var ahora = LocalDateTime.now();
        var horaConsulta = datosAgendarConsulta.fecha();
        var diferencia30minutos = Duration.between(ahora,horaConsulta).toMinutes()<30;

        if(diferencia30minutos){
            throw new ValidationException("Las consultas deben agendarse almenos con 30 mins de anticipación");
        }
    }
}
