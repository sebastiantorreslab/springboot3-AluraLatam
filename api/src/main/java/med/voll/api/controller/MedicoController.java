package med.voll.api.controller;
import jakarta.validation.Valid;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRespository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRespository medicoRespository;

    public MedicoController(MedicoRespository medicoRespository) {
        this.medicoRespository = medicoRespository;
    }

    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico medico){
        medicoRespository.save(new Medico(medico));
    }

    @GetMapping
    public List<Medico> listadoMedicos(){
        return medicoRespository.findAll();
    }
}
