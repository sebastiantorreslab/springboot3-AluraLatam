package med.voll.api.controller;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico medico){
        medicoRepository.save(new Medico(medico));
    }

    @GetMapping
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(size=2) Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }


    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid  DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.inactivar();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> detallar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalladoMedico(medico));
    }




}
