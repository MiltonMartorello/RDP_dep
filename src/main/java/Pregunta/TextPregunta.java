package Pregunta;

import java.util.ArrayList;
import java.util.List;

public class TextPregunta implements Pregunta<String> {

  List<String> posiblesRespuestas = new ArrayList<>();
  String titulo;

  public TextPregunta(String titulo, List<String> posiblesRespuestas) {
    this.titulo = titulo;
    this.posiblesRespuestas = posiblesRespuestas;
  }

  public String getTitulo(){
    return titulo;
  }

  public List<String> getPosiblesRespuestas() {return posiblesRespuestas;}
}