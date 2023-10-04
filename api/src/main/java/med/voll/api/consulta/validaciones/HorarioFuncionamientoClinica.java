package med.voll.api.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class HorarioFuncionamientoClinica implements ValidadorDeConsultas{


    public void validar(DatosAgendarConsulta datos){

        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesApertura = datos.fecha().getHour()<7;
        var despuesCierre = datos.fecha().getHour()>19;

        if(domingo || antesApertura || despuesCierre ){
            throw new ValidationException(("El horario de atención de la clínica es de lunes a viernes"));
        }



    }
}
