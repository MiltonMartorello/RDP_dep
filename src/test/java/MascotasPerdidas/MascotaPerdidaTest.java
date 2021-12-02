package MascotasPerdidas;

import Asociacion.Asociacion;
import Rescatista.FormularioMascotaEncontradaConChapita;
import Duenio.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MascotaPerdidaTest {
  LocalDate fechaInvalida = LocalDate.now().minusMonths(1);
  LocalDate fechaValida = LocalDate.now().minusDays(1);
  Asociacion asociacion = new Asociacion("Av de Mayo 130");
  Contacto hermano = new Contacto("Pirulo", "Martin", "123456","pirulo@gmail.com");
  List<Contacto> contactos = Arrays.asList(hermano);
  Contacto contactoRescatista = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");
  Duenio elDuenio = new Duenio("Camila", "Pirulo", LocalDate.now(), TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", Arrays.asList(MedioDeComunicacion.SMS, MedioDeComunicacion.MAIL));
  FormularioMascotaEncontradaConChapita juan = new FormularioMascotaEncontradaConChapita("Juan", "Flores", LocalDate.now(), TipoDocumento.DNI, "39908765", "Medrano 521", elDuenio, contactoRescatista);
  FormularioMascotaEncontradaConChapita manuel = new FormularioMascotaEncontradaConChapita("Manuel", "Pepito", LocalDate.now(), TipoDocumento.DNI, "39908765", "Medrano 521", elDuenio, contactoRescatista);

  /*@Test
  public void unicaMascotaEncontradaEnLosUltimos10Dias(){
    manuel.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "perrito caniche", "Moron");
    juan.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "perrito labrador", "Adrogue" );
    manuel.mascotaPerdida.setFechaEncontrado(fechaValida);
    juan.mascotaPerdida.setFechaEncontrado(fechaInvalida);
    elDuenio.setAsociacion(asociacion);
    assertEquals(asociacion.mascotasEncontradasEnLosUltimosDias(10).size(), 1);
  }*/

}
