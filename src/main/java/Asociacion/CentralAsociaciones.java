package Asociacion;

import Mascota.Mascota;
import Mascota.ValorCaracteristica;
import Rescatista.MascotaPerdida;
import Duenio.Contacto;
import Mascota.Animal;
import Duenio.Duenio;
import Duenio.MedioDeComunicacion;
import Duenio.PersistenceEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CentralAsociaciones extends PersistenceEntity {
  private static CentralAsociaciones instancia = null;

  public static CentralAsociaciones getInstance(){
    if(instancia== null){
      instancia = new CentralAsociaciones();
    }
    return instancia;
  }

  @OneToMany
  List<Asociacion> asociaciones = new ArrayList<Asociacion>();
  @OneToMany
  List<PublicacionMascotaPerdida> publicacionesPendientes = new ArrayList<PublicacionMascotaPerdida>();
  @OneToMany
  List<PublicacionMascotaPerdida> publicacionesAprobadas = new ArrayList<PublicacionMascotaPerdida>();
  @OneToMany
  List<PublicacionDarEnAdopcion> publicacionesDarAdopcion = new ArrayList<PublicacionDarEnAdopcion>();
  @OneToMany
  List<FormularioParaAdoptar> publicacionParaAdoptar = new ArrayList<FormularioParaAdoptar>();
  @Transient
  RepoDePreguntasGenerales repositorioPreguntasComunes;

  public CentralAsociaciones() {
  }

  public List<FormularioParaAdoptar> getPublicacionParaAdoptar() {
    return publicacionParaAdoptar;
  }

  public List<PublicacionDarEnAdopcion> getPublicacionesDarAdopcion() {
    return publicacionesDarAdopcion;
  }

  public void setAsociaciones(Asociacion asociacion){
    asociaciones.add(asociacion);
  }
  public Asociacion buscarAsociacionMasCercana(String direccion){
    //Buscar algoritmo de busqueda cercana
    return asociaciones.stream()
        .filter(asociacion -> asociacion.getDireccion().equals(direccion))
        .findFirst().get();
  }
  public void generarPublicacion(MascotaPerdida mascotaPerdida, Contacto contactoRescatista) {
    publicacionesPendientes.add(
    new PublicacionMascotaPerdida(buscarAsociacionMasCercana(mascotaPerdida.getLugar()), mascotaPerdida, contactoRescatista));
  }
  public void aprobarPublicacion(PublicacionMascotaPerdida publicacionMascotaPerdida){
    publicacionesAprobadas.add(publicacionMascotaPerdida);
    publicacionesPendientes.remove(publicacionMascotaPerdida);
  }
  public void rechazarPublicacion(PublicacionMascotaPerdida publicacionMascotaPerdida) {
    publicacionesPendientes.remove(publicacionMascotaPerdida);
  }
  public List<PublicacionMascotaPerdida> getPublicacionesPendientes() {
    return publicacionesPendientes;
  }

  public void generarPublicacionDarEnAdopcion(Mascota mascota, List<Respuesta> preguntasRespuestas, Duenio duenio){
    publicacionesDarAdopcion.add(new PublicacionDarEnAdopcion(mascota, preguntasRespuestas, duenio));
  }
  public void generarPublicacionAdoptar(Contacto contacto, List<Animal> preferencias , List<ValorCaracteristica> comodidades, List<MedioDeComunicacion> medio){
    publicacionParaAdoptar.add(new FormularioParaAdoptar(preferencias, comodidades, contacto, medio));
  }

}
