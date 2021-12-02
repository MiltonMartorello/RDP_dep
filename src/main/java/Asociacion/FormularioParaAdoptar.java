package Asociacion;

import Mascota.Animal;
import Mascota.ValorCaracteristica;
import Duenio.Contacto;
import Duenio.MedioDeComunicacion;
import Duenio.PersistenceEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormularioParaAdoptar extends PersistenceEntity {
  @Embedded
  Contacto contacto;
  @Enumerated
  @ElementCollection
  List<Animal> preferencias = new ArrayList<Animal>();
  @Transient
  List<ValorCaracteristica> comodidades = new ArrayList<ValorCaracteristica>();

  @ElementCollection
  @Enumerated
  private List<MedioDeComunicacion> medios = new ArrayList<>();

  public FormularioParaAdoptar(List<Animal> preferencias, List<ValorCaracteristica> comodidades, Contacto contacto, List<MedioDeComunicacion> medios){
    this.preferencias = preferencias;
    this.comodidades = comodidades;
    this.contacto = contacto;
    this.medios = medios;
  }

  public FormularioParaAdoptar() {

  }

  public Contacto getContacto() {
    return contacto;
  }

  public List<Animal> getPreferencias() {
    return preferencias;
  }

  public List<ValorCaracteristica> getComodidades() {
    return comodidades;
  }

  public List<MedioDeComunicacion> getMedioNotificacion() {
    return this.medios;
  }
}
