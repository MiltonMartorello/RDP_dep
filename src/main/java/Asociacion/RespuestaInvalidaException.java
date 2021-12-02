package Asociacion;

public class RespuestaInvalidaException extends RuntimeException {
    public RespuestaInvalidaException(String idDuenio) {
        super("La respuesta no pertenece a las alternativas posibles");
    }
}
