package db.persistencia;

import Asociacion.*;
import Caracteristica.*;
import Mascota.*;
import Pregunta.TextPregunta;
import Rescatista.FormularioMascotaEncontradaConChapita;
import Duenio.*;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
  @Test
  public void persistenciaDuenio() {
    Contacto hermano = new Contacto("Pirulo", "Martin", "123456","pirulo@gmail.com");
    List<Contacto> contactos = new ArrayList<Contacto>();
    contactos.add(hermano);
    LocalDate fechaNacimiento = LocalDate.parse("1996-06-23");
    Duenio jorge = new Duenio("Camila", "Pirulo", fechaNacimiento, TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", Arrays.asList(MedioDeComunicacion.MAIL, MedioDeComunicacion.SMS));

    entityManager().persist(jorge);
    assertNotNull(jorge.getId());

    Duenio jorgedb = entityManager().find(Duenio .class, jorge.getId());

    assertSame(jorgedb, jorge);
  }
  @Test
  public void persistenciaMascota() {
    List<ValorCaracteristica> caracteristicas = new ArrayList<ValorCaracteristica>();
    TextCaracteristica caracteristicaCastrado = new TextCaracteristica("castrado");
    ValorCaracteristica castrado = new ValorCaracteristica(caracteristicaCastrado,"SI");
    caracteristicas.add(castrado);
    caracteristicas.add(castrado);
    Mascota beagle = new Mascota(caracteristicas, Animal.PERRO, "Greta", "Gretuchis", 13, Sexo.FEMENINO, "Es de color negro, marron y blanco", null);

    entityManager().persist(beagle);
    assertNotNull(beagle.getId());

    Mascota perrodb = entityManager().find(Mascota .class, beagle.getId());

    assertSame(perrodb, beagle);
  }

  @Test
  public void persistenciaFormularioConChapita() {
    Asociacion asociacion = new Asociacion("Av de Mayo 130");
    List<Contacto> contactos = Arrays.asList(new Contacto("Pirulo", "Camila", "123456","eformaggio@frba.utn.edu.ar"));
    LocalDate fechaNacimiento = LocalDate.parse("1997-06-20");
    Contacto contactoRescatista = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");
    List<MedioDeComunicacion> medios = Arrays.asList(MedioDeComunicacion.MAIL, MedioDeComunicacion.SMS);
    Duenio duenio = new Duenio("Camila", "Pirulo", fechaNacimiento, TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", medios);

    FormularioMascotaEncontradaConChapita formularioMascotaEncontrada = new FormularioMascotaEncontradaConChapita("Juan", "Flores", fechaNacimiento, TipoDocumento.DNI, "39908765", "Medrano 521", duenio, contactoRescatista);
    entityManager().persist(formularioMascotaEncontrada);
    assertNotNull(formularioMascotaEncontrada.getId());

    FormularioMascotaEncontradaConChapita formularioMascotaEncontradaConChapitadb = entityManager().find(FormularioMascotaEncontradaConChapita .class, formularioMascotaEncontrada.getId());

    assertSame(formularioMascotaEncontradaConChapitadb, formularioMascotaEncontradaConChapitadb);
  }

  @Test
  public void persistenciaAsociacion() {
    Asociacion asociacion = new Asociacion("Av de Mayo 130");

    Contacto contacto = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");

    List<ValorCaracteristica> caracteristicas = new ArrayList<ValorCaracteristica>();
    TextCaracteristica caracteristicaCastrado = new TextCaracteristica("castrado");
    ValorCaracteristica castrado = new ValorCaracteristica(caracteristicaCastrado,"SI");
    caracteristicas.add(castrado);
    caracteristicas.add(castrado);

    List<Animal> animales= new ArrayList<>();
    animales.add(Animal.GATO);

    List<MedioDeComunicacion> mediosPreferidos = Arrays.asList(MedioDeComunicacion.MAIL, MedioDeComunicacion.SMS);

    CentralAsociaciones centralAsociaciones = new CentralAsociaciones();
    centralAsociaciones.generarPublicacionAdoptar(contacto,animales,caracteristicas,mediosPreferidos);

    Mascota beagle = new Mascota(caracteristicas, Animal.PERRO, "Greta", "Gretuchis", 13, Sexo.FEMENINO, "Es de color negro, marron y blanco", null);

    List<Contacto> contactos = Arrays.asList(new Contacto("Pirulo", "Camila", "123456","eformaggio@frba.utn.edu.ar"));
    LocalDate fechaNacimiento = LocalDate.parse("1997-06-20");
    Contacto contactoRescatista = new Contacto("Mendez", "Martin", "123456","mendez@gmail.com");
    List<MedioDeComunicacion> medios = Arrays.asList(MedioDeComunicacion.MAIL, MedioDeComunicacion.SMS);
    Duenio duenio = new Duenio("Camila", "Pirulo", fechaNacimiento, TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", medios);

    List<Respuesta> preguntas = new ArrayList<Respuesta>();
    Respuesta pregunta1 = new Respuesta(new TextPregunta("tiene patio", Arrays.asList("SI", "NO")), "SI");
    preguntas.add(pregunta1);

    FormularioDarEnAdopcion formularioDarEnAdopcion1 = new FormularioDarEnAdopcion(beagle,duenio,preguntas);
    asociacion.generarPublicacion(formularioDarEnAdopcion1);
    entityManager().persist(asociacion);
    entityManager().persist(centralAsociaciones);
    assertNotNull(asociacion.getId());
    assertNotNull(centralAsociaciones.getId());

    Asociacion asociaciondb = entityManager().find(Asociacion .class, asociacion.getId());

    CentralAsociaciones centralAsociacionesdb = entityManager().find(CentralAsociaciones .class, centralAsociaciones.getId());

    assertSame(asociaciondb, asociacion);
    assertSame(centralAsociacionesdb, centralAsociaciones);
  }

  @Test
  public void persistenciaPublicacion() {
    List<ValorCaracteristica> caracteristicas = new ArrayList<ValorCaracteristica>();
    TextCaracteristica caracteristicaCastrado = new TextCaracteristica("castrado");
    ValorCaracteristica castrado = new ValorCaracteristica(caracteristicaCastrado,"SI");
    caracteristicas.add(castrado);
    caracteristicas.add(castrado);
    Mascota beagle = new Mascota(caracteristicas, Animal.PERRO, "Greta", "Gretuchis", 13, Sexo.FEMENINO, "Es de color negro, marron y blanco", null);
    List<Respuesta> preguntas = new ArrayList<Respuesta>();
    Respuesta pregunta1 = new Respuesta(new TextPregunta("tiene patio", Arrays.asList("SI", "NO")), "SI");
    preguntas.add(pregunta1);

    List<Contacto> contactos = Arrays.asList(new Contacto("Pirulo", "Camila", "123456","eformaggio@frba.utn.edu.ar"));
    LocalDate fechaNacimiento = LocalDate.parse("1997-06-20");
    List<MedioDeComunicacion> medios = Arrays.asList(MedioDeComunicacion.MAIL, MedioDeComunicacion.SMS);
    Duenio duenio = new Duenio("Camila", "Pirulo", fechaNacimiento, TipoDocumento.DNI, "42465789", contactos,"Camila","contra5647", medios);


    PublicacionDarEnAdopcion publicacionDarEnAdopcion = new PublicacionDarEnAdopcion(beagle, preguntas, duenio);

    entityManager().persist(publicacionDarEnAdopcion);
    assertNotNull(publicacionDarEnAdopcion.getId());

    PublicacionDarEnAdopcion publicacionDarEnAdopciondb = entityManager().find(PublicacionDarEnAdopcion .class, publicacionDarEnAdopcion.getId());

    assertSame(publicacionDarEnAdopciondb, publicacionDarEnAdopcion);
  }

}
