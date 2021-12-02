package Asociaciones;

import Asociacion.*;
import Rescatista.FormularioMascotaEncontradaSinChapita;
import Duenio.*;
import org.junit.jupiter.api.Test;
import services.hogares.ServicioHogares;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PublicacionesTest {
  private CentralAsociaciones centralAsociaciones =  CentralAsociaciones.getInstance();

  @Test
  public void aprobarPublicacionQuitaLaPublicacionDeLaListaDePendientes() {
    Asociacion asociacion = new Asociacion("Av de Mayo 130");
    LocalDate fechaNacimiento = LocalDate.parse("1997-06-20");
    Contacto hermano = new Contacto("Pirulo", "Martin", "123456", "pirulo@gmail.com");
    List<Contacto> contactos = new ArrayList<Contacto>();
    contactos.add(hermano);
    Contacto contactoRescatista = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");

    centralAsociaciones.setAsociaciones(asociacion);
    FormularioMascotaEncontradaSinChapita formularioMascotaEncontrada = new FormularioMascotaEncontradaSinChapita("Juan", "Flores", fechaNacimiento, TipoDocumento.DNI, "39908765", "Malabia 500", contactoRescatista);
    //Ver la diferencia entre direccion rescatista y direccion mascotaPerdida
    formularioMascotaEncontrada.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "Hembra de color negro aproximadamente 2kg", "Av de Mayo 130");
    long cantidadPublicaciones = centralAsociaciones.getPublicacionesPendientes().stream().count();
    centralAsociaciones.aprobarPublicacion(centralAsociaciones.getPublicacionesPendientes().get(0));
    long cantidadPublicacionesDespues = centralAsociaciones.getPublicacionesPendientes().stream().count();
    assertEquals(cantidadPublicacionesDespues, cantidadPublicaciones -1);
  }
}