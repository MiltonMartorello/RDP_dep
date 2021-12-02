package Rescatista;
import Duenio.Contacto;
import Duenio.PersistenceEntity;
import Duenio.TipoDocumento;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "formularios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType = DiscriminatorType.STRING,
        name = "tipoFormulario"
)
public abstract class FormularioMascotaEncontrada extends PersistenceEntity {
  @Column(name = "nombre", insertable = false, updatable = false)
  private String nombre;
  @Column(name = "apellido", insertable = false, updatable = false)
  private String apellido;
  private LocalDate fechaDeNacimiento;
  @Embedded
  private TipoDocumento tipoDocumento;
  private String numeroDeDocumento;
  protected String direccion;
  @OneToOne
  public MascotaPerdida mascotaPerdida;
  @Embedded
  public Contacto contacto;

  public FormularioMascotaEncontrada(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, String numeroDeDocumento, String direccion, Contacto contacto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.numeroDeDocumento = numeroDeDocumento;
    this.direccion = direccion;
    this.contacto = contacto;
  }
  public FormularioMascotaEncontrada(){

  }

  public void darAvisoMascotaEncontrada(List<String> fotos, String descripcion, String lugar){
    this.mascotaPerdida = new MascotaPerdida(fotos, descripcion, lugar);
    notificacionMascotaEncontrada(mascotaPerdida);
  }

  abstract void notificacionMascotaEncontrada(MascotaPerdida mascotaPerdida);

  public MascotaPerdida getMascotaPerdida() {
    return mascotaPerdida;
  }

}
