package med.voll.api.medico;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;



@Entity(name="medico")
@Table(name="medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico medico) {
        this.nombre = medico.nombre();
        this.email = medico.email();
        this.documento = medico.documento();
        this.especialidad = medico.especialidad();
        this.telefono = medico.telefono();
        this.direccion = new Direccion(medico.direccion());
    }
}
