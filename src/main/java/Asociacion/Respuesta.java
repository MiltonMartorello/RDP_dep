package Asociacion;

import Pregunta.TextPregunta;

public class Respuesta{
  TextPregunta pregunta;
  String valor;

  public Respuesta(TextPregunta pregunta, String valor) {
    if (!pregunta.getPosiblesRespuestas().contains(valor)) { throw new RespuestaInvalidaException("Respuesta invalida");}
    this.pregunta = pregunta;
    this.valor = valor;
  }

  public TextPregunta getPregunta() {
    return pregunta;
  }

  public String getValor() {
    return valor;
  }
}