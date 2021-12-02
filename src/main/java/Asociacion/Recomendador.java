package Asociacion;

import Duenio.Contacto;
import Mascota.ValorCaracteristica;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Recomendador {

  CentralAsociaciones centralAsociaciones;
  Matcheador macheador = new Matcheador(centralAsociaciones);
  public Recomendador(CentralAsociaciones centralAsociaciones) {
    this.centralAsociaciones = centralAsociaciones;
  }

  public void generarRecomendacionSemanal(){
    Map<FormularioParaAdoptar,PublicacionDarEnAdopcion> adoptantesRecomendar = macheador.realizarMatch();
    adoptantesRecomendar.forEach((formularioParaAdoptar, publicacion) ->
        formularioParaAdoptar.getMedioNotificacion().
            forEach(medioComunicacion -> medioComunicacion
                .enviarRecomendacion(formularioParaAdoptar.getContacto(), publicacion)));
    }

    private boolean matcheaCon(PublicacionDarEnAdopcion publicacion, FormularioParaAdoptar adoptante) {
        List<Respuesta> respuestas = publicacion.getPreguntasRespuestas();
        List<ValorCaracteristica> comodidades = adoptante.getComodidades();

        return matchEdad(respuestas, comodidades) && matchOtrasMascotas(respuestas, comodidades);
    }

    private boolean matchOtrasMascotas(List<Respuesta> respuestas, List<ValorCaracteristica> comodidades) {
        String otrasMascotas = respuestas.stream().filter(respuesta -> filtrarPorString(respuesta, "Otras mascotas")).collect(Collectors.toList()).get(0).getValor();
        String otrasMascotasAdoptante = comodidades.stream().filter(comodidad -> comodidad.getCaracteristica().getTitulo() == "Otras Mascotas").collect(Collectors.toList()).get(0).getValor();

        return otrasMascotas == otrasMascotasAdoptante;
    }
    private boolean matchEdad(List<Respuesta> respuestas, List<ValorCaracteristica> comodidades) {
      Respuesta edadString = respuestas.stream().filter(respuesta -> filtrarPorString(respuesta, "Edad")).collect(Collectors.toList()).get(0);
      int edad = Integer.parseInt(edadString.getValor());


      ValorCaracteristica edadMaximaString = comodidades.stream().filter(comodidad -> filtrarPorString(comodidad, "Edad Maxima")).collect(Collectors.toList()).get(0);
      int edadMaxima = Integer.parseInt(edadMaximaString.getValor());

      return edadMaxima > edad;
    }
    public Boolean filtrarPorString(Respuesta respuesta, String string){
      return respuesta.getPregunta().getTitulo().equals(string);
    }
    public Boolean filtrarPorString(ValorCaracteristica valorCaracteristica, String string){
      return valorCaracteristica.getCaracteristica().getTitulo().equals(string);
    }
}
