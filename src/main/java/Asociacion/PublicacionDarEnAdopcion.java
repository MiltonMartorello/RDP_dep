package Asociacion;

import Mascota.Mascota;
import Duenio.Duenio;
import Duenio.PersistenceEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
@Entity
public class PublicacionDarEnAdopcion extends PersistenceEntity {
  @ManyToOne
  private Duenio duenio;
  @ManyToOne
  private Mascota mascota;
  @Transient
  private List<Respuesta> preguntasRespuestas = new ArrayList<>() ;

  public PublicacionDarEnAdopcion(Mascota mascota, List<Respuesta> preguntasRespuestas, Duenio duenio) {
    this.mascota =  mascota;
    this.preguntasRespuestas = preguntasRespuestas;
    this.duenio = duenio;
  }

  public PublicacionDarEnAdopcion() {

  }

  public void notificarDuenioAdopcion() {
    this.duenio.getMedioNotificacion()
        .forEach(medioNotificacion -> medioNotificacion.notificarDuenio(duenio, "Quiero dar en adopción mi mascota", this.mascota.getNombre()));
  }

  public Mascota getMascota() {
    return mascota;
  }



  public List<Respuesta> getPreguntasRespuestas() {
    return preguntasRespuestas;
  }
}
