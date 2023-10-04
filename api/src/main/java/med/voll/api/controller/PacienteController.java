package med.voll.api.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
        repository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<DatosListaPaciente> listar(@PageableDefault(page=0,size=10, sort={"nombre"}) Pageable paginacion) {
        return repository.findByActivoTrue(paginacion).map(DatosListaPaciente::new);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformacion(datos);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.inactivar();
    }

}
