package med.voll.api.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Transactional
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico medico){
        medicoRespository.save(new Medico(medico));
    }

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size=2) Pageable paginacion){
        return medicoRespository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }


    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid  DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRespository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        Medico medico = medicoRespository.getReferenceById(id);
        medico.inactivar();
    }




}
