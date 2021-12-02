package Duenio;

import Asociacion.Asociacion;
import ChequeoClave.ChequeadorDeClaves;
import ChequeoClave.ClaveInseguraException;
import Mascota.Mascota;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Duenio extends PersistenceEntity {

  private String nombre;
  private String apellido;
  private LocalDate fechaDeNacimiento;
  private String user;
  private String password;

  @Embedded
  private TipoDocumento tipoDeDocumento;

  private String numeroDeDocumento;

  @ManyToOne(cascade=CascadeType.ALL)
  private Asociacion asociacion;

  @ElementCollection
  private List<Contacto> contactos=new ArrayList<Contacto>();

  @OneToMany(cascade=CascadeType.ALL)
  private List<Mascota> mascotas=new ArrayList<Mascota>();

  @ElementCollection
  @Enumerated
  private List<MedioDeComunicacion> medios;

  // es probable que este idDuenio necesite un preprocesado de algun otro objeto que genere IDs
  public Duenio(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDeDocumento, String numeroDeDocumento, List<Contacto> contactos, String user, String password, List<MedioDeComunicacion> medios) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDeDocumento = tipoDeDocumento;
    this.numeroDeDocumento = numeroDeDocumento;
    this.validarContacto(contactos);
    this.user=user;
    this.password=password;
    this.medios = medios;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public LocalDate getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public TipoDocumento getTipoDeDocumento() {
    return tipoDeDocumento;
  }

  public void setTipoDeDocumento(TipoDocumento tipoDeDocumento) {
    this.tipoDeDocumento = tipoDeDocumento;
  }

  public String getNumeroDeDocumento() {
    return numeroDeDocumento;
  }

  public void setNumeroDeDocumento(String numeroDeDocumento) {
    this.numeroDeDocumento = numeroDeDocumento;
  }

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public void setContactos(List<Contacto> contactos) {
    this.contactos = contactos;
  }

  public List<Mascota> getMascotas() {
    return mascotas;
  }

  public void setMascotas(List<Mascota> mascotas) {
    this.mascotas = mascotas;
  }

  public List<MedioDeComunicacion> getMedios() {
    return medios;
  }

  public void setMedios(List<MedioDeComunicacion> medios) {
    this.medios = medios;
  }

  public Duenio(){

  }

  public void registrarPersona(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  private void darEnAdopcion(){

  }

  private void validarContacto(List<Contacto> contactos) {
    if(contactos.isEmpty()) {
      throw new PersonaInvalidaException("le falta como minimo un contacto");
    }
    this.contactos=contactos;
  }

  protected void registrarMascota(Mascota mascota){
    mascotas.add(mascota);
  }

  public String getMail(){
    return this.contactos.get(0).getMail();
  }

  public String getTelefono() { return this.contactos.get(0).getTelefono(); }

  public List<MedioDeComunicacion> getMedioNotificacion() {
    return this.medios;
  }

  public List<Contacto> getContacto() {
    return contactos;
  }

  public String getPassword() {
    return password;
  }

  public String getUser() {
    return user;
  }

  public void chequearCalidadDecontrasenia(String nuevaContrasenia) {
    if (!ChequeadorDeClaves.getChequeadorDeClavesInstance().esClaveSegura(nuevaContrasenia)) {
      throw new ClaveInseguraException("La clave es insegura");
    }
  }

  public void transformarContraseña(String user, String password) {
    //chequearCalidadDecontrasenia(password);
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(password.getBytes());
      byte[] bytes = md.digest();
      StringBuilder sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }
      this.password = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new ContraseniaInvalida("Constrasenia invalida", e);
    }
    this.user = user;
  }

}

