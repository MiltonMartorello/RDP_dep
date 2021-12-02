package Rescatista;

import Duenio.*;
import Asociacion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FormularioMascotaPerdidaTest {
  private Asociacion asociacion;
  private List<Contacto> contactos;
  private LocalDate fechaNacimiento;
  private Duenio duenio;
  private Contacto contactoRescatista;
  private List<MedioDeComunicacion> medios;
  private MedioDeComunicacion mailMock;

  private CentralAsociaciones centralAsociaciones =  CentralAsociaciones.getInstance();

  @BeforeEach
  void initFormularioMascotaPerdida() {
    asociacion = new Asociacion("Av de Mayo 130");
    contactos = Arrays.asList(new Contacto("Pirulo", "Camila", "123456","eformaggio@frba.utn.edu.ar"));
    fechaNacimiento = LocalDate.parse("1997-06-20");
    contactoRescatista = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");
    mailMock = mock(MedioDeComunicacion.class);
    medios = Arrays.asList(mailMock);

    duenio = new Duenio("Camila", "Pirulo", fechaNacimiento, TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", medios);
    duenio.setAsociacion(asociacion);

  }
  @Test
  public void GuardarMascotaPerdidaConChapitaEncontrada() {
    FormularioMascotaEncontradaConChapita formularioMascotaEncontrada = new FormularioMascotaEncontradaConChapita("Juan", "Flores", fechaNacimiento, TipoDocumento.DNI, "39908765", "Medrano 521", duenio, contactoRescatista);
    formularioMascotaEncontrada.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "Hembra de color negro aproximadamente 2kg", "Malabia 500");
    assertTrue(duenio.getAsociacion().getFormularioMascotaEncontradasConChapita().contains(formularioMascotaEncontrada));
  }
  @Test
  public void InformarMascotaPerdidaConChapitaEncontrada() {
    FormularioMascotaEncontradaConChapita formularioMascotaEncontrada =
            new FormularioMascotaEncontradaConChapita("Juan",
                    "Flores",
                    fechaNacimiento,
                    TipoDocumento.DNI,
                    "39908765",
                    "Medrano 521",
                    duenio, contactoRescatista);
    formularioMascotaEncontrada.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "Hembra de color negro aproximadamente 2kg", "Malabia 500");
    verify(mailMock, times(1)).notificarDuenio(eq(duenio), eq("Encontramos a tu mascota"),eq("Malabia 500"));
    //verify(smsMock, times(1)).notificarDuenio(eq(duenio), eq("Encontramos a tu mascota"),eq("Malabia 500"));
  }

  @Test
  public void GenerarPublicacionMascotaPerdidaSinChapitaEncontrada() {
    centralAsociaciones.setAsociaciones(asociacion);
    FormularioMascotaEncontradaSinChapita formularioMascotaEncontrada = new FormularioMascotaEncontradaSinChapita("Juan", "Flores", fechaNacimiento, TipoDocumento.DNI, "39908765", "Malabia 500", contactoRescatista);
    //Ver la diferencia entre direccion rescatista y direccion mascotaPerdida
    formularioMascotaEncontrada.darAvisoMascotaEncontrada(Arrays.asList("foto.jpg"), "Hembra de color negro aproximadamente 2kg", "Av de Mayo 130");
    assertFalse(centralAsociaciones.getPublicacionesPendientes().isEmpty());
  }
}
