package Asociacion;

import Rescatista.MascotaPerdida;
import Duenio.Contacto;
import Duenio.PersistenceEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PublicacionMascotaPerdida extends PersistenceEntity {
  @OneToOne
  private Asociacion asociacion;
  @OneToOne
  private MascotaPerdida mascotaPerdida;
  @Embedded
  private Contacto contactoRescatista;

  public PublicacionMascotaPerdida(Asociacion asociacion, MascotaPerdida mascotaPerdida, Contacto contactoRescatista) {
    this.asociacion =  asociacion;
    this.mascotaPerdida = mascotaPerdida;
    this.contactoRescatista = contactoRescatista;
  }

  public PublicacionMascotaPerdida() {

  }
}
