package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.direccion.Direccion;

@Entity(name = "paciente")
@Table(name = "pacientes")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @Column(name="documento_identidad")
    private String documentoIdentidad;
    private String telefono;

    @Embedded
    private Direccion direccion;

    private Boolean activo;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarInformacion(DatosActualizacionPaciente datos) {
        if (datos.nombre() != null)
            this.nombre = datos.nombre();

        if (datos.telefono() != null)
            this.telefono = datos.telefono();

        if (datos.direccion() != null)
            direccion.actualizarDatos(datos.direccion());
    }


    public void inactivar() {
        this.activo = false;
    }
}
