package Duenio;
import Caracteristica.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaracteristicasTest {
  RepositorioCaracteristica repositorio;
  @BeforeEach
  void initFormularioMascotaPerdida() {
    repositorio = new RepositorioCaracteristica();
  }

  @Test
  public void PuedeCrearUnaCaracteristicaText(){
    TextCaracteristica caracteristica = new TextCaracteristica("Castrado");
    repositorio.agregarCaracteristica(caracteristica);

    assertEquals(repositorio.getCaracteristicas(), Arrays.asList(caracteristica));
  }
}

