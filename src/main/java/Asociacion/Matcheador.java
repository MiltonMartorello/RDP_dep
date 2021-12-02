package Asociacion;

import Duenio.Contacto;
import Mascota.ValorCaracteristica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Matcheador {

  Map<FormularioParaAdoptar,PublicacionDarEnAdopcion> contactosParaRecomendar = new HashMap<>();
  CentralAsociaciones centralAsociaciones;
  public Matcheador(CentralAsociaciones centralAsociaciones) {
    this.centralAsociaciones = centralAsociaciones;
  }
  public Map<FormularioParaAdoptar,PublicacionDarEnAdopcion> realizarMatch(){
    for (FormularioParaAdoptar quiereAdoptar: centralAsociaciones.getPublicacionParaAdoptar()) {
      for (PublicacionDarEnAdopcion quiereDarEnAdopcion : centralAsociaciones.getPublicacionesDarAdopcion()) {
        for (ValorCaracteristica valorCaracteristica: quiereAdoptar.getComodidades()) {
          for (Respuesta respuesta: quiereDarEnAdopcion.getPreguntasRespuestas()) {
            if (valorCaracteristica.getCaracteristica().getTitulo().equals(respuesta.getPregunta().getTitulo())){
              if (valorCaracteristica.getValor().equals(respuesta.getValor())){
                contactosParaRecomendar.put(quiereAdoptar, quiereDarEnAdopcion);
              }
            }
          }
        }
      }
    }
    return contactosParaRecomendar;
  }
  /*public  coincidePregunta(List<ValorCaracteristica> comodidadesQuiereAdoptar, List<Respuesta> comodidadesDarEnAdopcion){
    for (ValorCaracteristica valorCaracteristica: comodidadesQuiereAdoptar) {
      for (Respuesta respuesta: comodidadesDarEnAdopcion) {
          if (valorCaracteristica.getCaracteristica().getTitulo().equals(respuesta.getPregunta().getTitulo())){
            if (valorCaracteristica.getValor().equals(respuesta.getValor())){
              contactosParaRecomendar.put()
            }
          }
      }
    }
  }*/
}
