package services.hogares;

import org.junit.jupiter.api.Test;
import services.hogares.entities.Hogar;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
* Formateador de JSON
* https://codebeautify.org/jsonviewer
* Asi se consultan las capacidades de los hogares => hogares[].{Nombre: nombre, Capacidad: capacidad}
* */

class HogaresServiceTest {
  ServicioHogares servicioHogares = ServicioHogares.instancia();
  List<Hogar> listadoDeHogares = servicioHogares.listadoDeHogares();

//los test rompen, si cambia el servicio de hogares.

  HogaresServiceTest() throws IOException {}

  @Test
  public void hayUnTotalDe40Hogares () { //borrar
    System.out.print("Cantidad de hogares => "+listadoDeHogares.size());
    assertEquals(listadoDeHogares.size(),40); //mock

  }
  @Test
  public void algunHogarConPatio () {
    System.out.println("Hogares con patio => "+ listadoDeHogares
        .stream()
        .filter(Hogar::isPatio).count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(Hogar::isPatio)
    );
  }

  @Test
  public void algunHogarDisponibleConAdminisionDeGatos () {
    System.out.println("Hogares disponibles que aceptan gatos => "+ listadoDeHogares
        .stream()
        .filter(hogar -> hogar.admiteGato() && hogar.tieneCapacidad())
        .count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(hogar -> hogar.admiteGato() && hogar.tieneCapacidad()));
  }

  @Test
  public void algunHogarCon30LugaresParaPerros() {
    System.out.println("Hogares con 30 espacio para perros => "+ listadoDeHogares
        .stream()
        .filter(hogar -> hogar.admitePerro() && hogar.getLugaresDisponibles() > 30)
        .count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(hogar -> hogar.admitePerro() && hogar.getLugaresDisponibles() > 30)
    );
  }

  @Test
  public void algunHogarQueAceptePerrosMansos() {
    //Manso
    System.out.println("Hogares con espacio para perros mansos => "+ listadoDeHogares
        .stream()
        .filter(hogar -> hogar.admitePerro() && hogar.getCaracteristicas().contains("Manso"))
        .count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(hogar -> hogar.admitePerro() && hogar.getCaracteristicas().contains("Manso")));
  }

  @Test
  public void algunHogarNoEspecificaCaracteristicas() {
    //Manso
    System.out.println("Hogares que no especifican caracteristicas => "+ listadoDeHogares
        .stream()
        .filter(hogar -> hogar.getCaracteristicas().isEmpty())
        .count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(hogar -> hogar.getCaracteristicas().isEmpty()));
  }

  @Test
  public void hogarConCapacidadEnCordoba() {
    System.out.println("Hogares en Córdoba con lugar disponible => "+listadoDeHogares
        .stream()
        .filter(hogar -> hogar.tieneCapacidad() && hogar.getUbicacion().getDireccion().contains("Córdoba"))
        .count());
    assertTrue(listadoDeHogares
        .stream()
        .anyMatch(hogar -> hogar.tieneCapacidad() && hogar.getUbicacion().getDireccion().contains("Córdoba")));
  }
}
