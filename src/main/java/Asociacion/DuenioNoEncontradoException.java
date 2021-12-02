package Asociacion;

public class DuenioNoEncontradoException extends RuntimeException {
  public DuenioNoEncontradoException(String idDuenio) {
    super("No se encotró el dueño de id: "+ idDuenio);
  }
}
