package Duenio;

import javax.persistence.Embeddable;

@Embeddable
public class Contacto {
  public String apellido;
  public String nombre;
  public String telefono;
  public String mail;

  public Contacto(String apellido, String nombre, String telefono, String mail) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.telefono = telefono;
    this.mail = mail;
  }

  public Contacto() {

  }

  public String getMail() {
    return mail;
  }
  public String getTelefono() { return telefono; }

}
