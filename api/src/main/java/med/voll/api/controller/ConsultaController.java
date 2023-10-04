package med.voll.api.controller;


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
public class ConsultaController {

    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> agendar (@RequestBody @Valid DatosAgendarConsulta datos){
        consultaService.agendar(datos);
        return  ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));
    }

}
