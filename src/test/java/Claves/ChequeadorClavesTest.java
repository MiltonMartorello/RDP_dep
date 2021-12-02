package Claves;

import ChequeoClave.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChequeadorClavesTest {

  final ChequeadorDeClaves chequeadorDeClaves = ChequeadorDeClaves.getChequeadorDeClavesInstance();


  @Test
  public void unaClaveConMenosDeOchoCaracteresNoRespetaLaCantidadMinima() {
    String clave = "disdsis";
    Boolean resultado = chequeadorDeClaves.esClaveSegura(clave);
    assertFalse(resultado);
  }

  @Test
  public void unaClaveConMasDeOchoCaracteresRespetaLaCantidadMinima() {
    String clave = "GrupoNueve";
    Boolean resultado = chequeadorDeClaves.esClaveSegura(clave);
    assertTrue(resultado);
  }

  @Test
  public void unaClaveNoPuedeSerUnaPalabraComun() {
    RepoDeClavesComunes repoDeClavesComunes = new RepoDeClavesComunes();

    Set<String> clavesComunes = RepoDeClavesComunes.getClavesComunes();
    String clave = clavesComunes.stream().findFirst().get();
    Boolean resultado = chequeadorDeClaves.esClaveSegura(clave);
    assertFalse(resultado);
  }

  @Test
  public void unaClaveNULLEsInvalida() {
    Boolean resultado = chequeadorDeClaves.esClaveSegura(null);
    assertFalse(resultado);
  }

  @Test
  public void unaClaveEnBlancoEsInvalida() {
    String clave = "";
    Boolean resultado = chequeadorDeClaves.esClaveSegura(clave);
    assertFalse(resultado);
  }

  @Test
  public void unaClaveQueRespetaLasReglasAnterioresEsValida() {
    String clave = "pinkfloydSinComun";
    Boolean resultado = chequeadorDeClaves.esClaveSegura(clave);
    assertTrue(resultado);
  }
}