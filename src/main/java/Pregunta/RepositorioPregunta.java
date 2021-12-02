package Pregunta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPregunta {
  private static RepositorioPregunta repoDePreguntasInstance = new RepositorioPregunta();
  private List<TextPregunta> preguntas = new ArrayList<TextPregunta>();

  public static RepositorioPregunta getRepoDeCaracteristicasInstance() {
    return repoDePreguntasInstance;
  }
  public void agregarPregunta(TextPregunta pregunta){
    preguntas.add(pregunta);
  }
  public List<TextPregunta> getPreguntas(){
    return preguntas;
  }
}
