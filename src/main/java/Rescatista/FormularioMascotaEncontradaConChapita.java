package Rescatista;

import Asociacion.Asociacion;
import Duenio.Contacto;
import Duenio.Duenio;
import Duenio.TipoDocumento;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "formulariosConChapita")
@DiscriminatorValue("C")
public class FormularioMascotaEncontradaConChapita extends FormularioMascotaEncontrada {
  @OneToOne
  private Duenio duenio;
  public FormularioMascotaEncontradaConChapita(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, String numeroDeDocumento, String direccion, Duenio duenio, Contacto contacto) {
    super(nombre, apellido, fechaDeNacimiento, tipoDocumento, numeroDeDocumento, direccion, contacto);
    this.duenio = duenio;
  }

  @Override
  public void notificacionMascotaEncontrada(MascotaPerdida mascotaPerdida) {
    notificarMascotaEncontrada(mascotaPerdida);
    duenio.getAsociacion().registrarFormulario(this);
  }

  public void notificarMascotaEncontrada(MascotaPerdida mascotaPerdida) {
    this.duenio.getMedioNotificacion()
        .forEach(medioDeNotificacion -> medioDeNotificacion.notificarDuenio(duenio, "Encontramos a tu mascota", mascotaPerdida.lugar));
  }

  public FormularioMascotaEncontradaConChapita(){}
}