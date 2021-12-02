package Asociacion;

import Caracteristica.TextCaracteristica;
import Duenio.PersistenceEntity;

import java.util.List;

public class RepoDePreguntasGenerales {

  List<TextCaracteristica> preguntas;

  public List<TextCaracteristica> getPreguntas() {
    return preguntas;
  }

  public RepoDePreguntasGenerales() {
  }

  private void agregarPreguntaGeneral(TextCaracteristica pregunta) {
    preguntas.add(pregunta);
  }

  private void removerPreguntaGeneral(TextCaracteristica pregunta) { preguntas.remove(pregunta); }
}
