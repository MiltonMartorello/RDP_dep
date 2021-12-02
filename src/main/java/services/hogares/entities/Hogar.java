package services.hogares.entities;

import java.util.List;

public class Hogar {
  public String id;
  public String nombre;
  private String telefono;
  private int capacidad;
  private int lugares_disponibles;
  private boolean patio;
  private Ubicacion ubicacion;
  private Admision admisiones;
  private List<String> caracteristicas;

  public Hogar() {
  }

  @Override
  public String toString() {
    return "Un hogar de nombre "+getNombre()+ " teléfono: "+getTelefono() +". Capacidad de: "+getCapacidad()+ " Lugares disponibles: "+ getLugaresDisponibles() + " y patio: "+isPatio();
  }

  private String getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTelefono() {
    return telefono;
  }

  private int getCapacidad() {
    return capacidad;
  }

  public int getLugaresDisponibles() {
    return lugares_disponibles;
  }

  public boolean isPatio() {
    return patio;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public boolean admiteGato() {
    return admisiones.deGatos();
  }
  public boolean admitePerro() {
    return admisiones.dePerros();
  }
  public boolean tieneCapacidad() {
    return capacidad > 0;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

}

