package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.consulta.ConsultaService;
import med.voll.api.consulta.DatosAgendarConsulta;
import med.voll.api.consulta.DatosDetalleConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar(@RequestBody @Valid DatosAgendarConsulta datos){
        var response = consultaService.agendar(datos);
        return  ResponseEntity.ok(response);
    }

}
