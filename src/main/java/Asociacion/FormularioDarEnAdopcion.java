package Asociacion;

import Mascota.*;

import Duenio.Duenio;
import Duenio.PersistenceEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormularioDarEnAdopcion extends PersistenceEntity {
  @OneToOne
  Mascota mascota;
  @ManyToOne
  Duenio duenio;
  @Transient
  List<Respuesta> preguntasRespuestas = new ArrayList<Respuesta>();

  public FormularioDarEnAdopcion(Mascota mascota, Duenio duenio, List<Respuesta> preguntasRespuestas) {
    this.mascota = mascota;
    this.duenio = duenio;
    this.preguntasRespuestas = preguntasRespuestas;
  }

  public FormularioDarEnAdopcion() {

  }
}
