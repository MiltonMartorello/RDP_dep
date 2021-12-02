package Rescatista;

import Asociacion.*;
import Duenio.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
@Entity
@Table(name = "formulariosSinChapita")
@DiscriminatorValue("S")
public class FormularioMascotaEncontradaSinChapita extends FormularioMascotaEncontrada{
  @Transient
  private CentralAsociaciones centralAsociaciones;

    public FormularioMascotaEncontradaSinChapita(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, String numeroDeDocumento, String direccion, Contacto contacto) {
    super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDeDocumento, direccion, contacto);
  }

  @Override
  public void notificacionMascotaEncontrada(MascotaPerdida mascotaPerdida) {
    centralAsociaciones.getInstance().generarPublicacion(mascotaPerdida, contacto);
  }

  public FormularioMascotaEncontradaSinChapita(){}
}
