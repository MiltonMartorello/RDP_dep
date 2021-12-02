package Pregunta;

public class BoolPregunta implements Pregunta<Boolean> {

  String titulo;

  public BoolPregunta(String titulo) {
    this.titulo = titulo;
  }

  public String getTitulo(){ return titulo; }
}
