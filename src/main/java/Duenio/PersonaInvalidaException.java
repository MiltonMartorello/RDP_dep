package Duenio;

class PersonaInvalidaException extends RuntimeException{
  public PersonaInvalidaException(String causa) {
    super("La persona es invalida porque" + causa);
  }
}
