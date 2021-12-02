package Asociacion;

import Caracteristica.Caracteristica;
import Rescatista.*;
import Rescatista.FormularioMascotaEncontradaConChapita;
import Rescatista.MascotaPerdida;
import Duenio.PersistenceEntity;
import Caracteristica.TextCaracteristica;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Asociacion extends PersistenceEntity {
  @OneToMany
  private List<FormularioMascotaEncontradaConChapita> formularioMascotaEncontradas = new ArrayList<>();
  @Transient
  private List<TextCaracteristica> preguntasAdopcion = new ArrayList<>();
  @OneToMany
  private List<FormularioDarEnAdopcion> publicaciones = new ArrayList<>();
  public String direccion;

  public Asociacion() {

  }

  @Transient
  public List<TextCaracteristica> getPreguntasAdopcion() {
    return preguntasAdopcion;
  }

  public void setPreguntasAdopcion(List<TextCaracteristica> preguntasAdopcion) {
    this.preguntasAdopcion = preguntasAdopcion;
  }

  public void agregarPreguntaAdopcion(TextCaracteristica pregunta) {
    preguntasAdopcion.add(pregunta);
  }

  public void removerPreguntaAdopcion(TextCaracteristica pregunta) {
    preguntasAdopcion.remove(pregunta);
  }

  public Asociacion(String direccion) {
    this.direccion = direccion;
  }

  public List<MascotaPerdida> mascotasEncontradasEnLosUltimosDias(int dias) {
    return formularioMascotaEncontradas
        .stream()
        .map(FormularioMascotaEncontrada::getMascotaPerdida)
        .filter(mascotaPerdida -> mascotaPerdida.encontradaEnTalDia(dias))
        .collect(Collectors.toList());
  }

  public List<FormularioMascotaEncontradaConChapita> getFormularioMascotaEncontradasConChapita(){ return formularioMascotaEncontradas;}

  public void registrarFormulario(FormularioMascotaEncontradaConChapita formulario) {
    formularioMascotaEncontradas.add(formulario);
  }
  public String getDireccion() {
    return direccion;
  }

  // TODO revisar como se construye
  public void generarPublicacion(FormularioDarEnAdopcion publicacion) {
    publicaciones.add(publicacion);
  }
}
