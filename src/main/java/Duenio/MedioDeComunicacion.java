package Duenio;

import Asociacion.PublicacionDarEnAdopcion;
import NotificacionAppRescateDePatitas.JavaMailApi;
import NotificacionAppRescateDePatitas.SMSApi;

import javax.persistence.Entity;
import java.util.List;

public enum MedioDeComunicacion {
    SMS() {
      SMSApi smsApi = new SMSApi();
      @Override
      public void notificarDuenio(Duenio duenio, String titulo, String cuerpo){
        smsApi.enviarSms(duenio.getMail(),titulo,cuerpo);
      }
      @Override
      public void enviarRecomendacion(Contacto contacto, PublicacionDarEnAdopcion publicaciones){
        smsApi.enviarRemcomendacion(contacto.getTelefono(), publicaciones);
      }
    },
    MAIL() {
      JavaMailApi javaMailApi = new JavaMailApi();
      @Override
      public void notificarDuenio(Duenio duenio, String titulo, String cuerpo){
        javaMailApi.enviarMail(duenio.getMail(), titulo, cuerpo);
      }
      @Override
      public void enviarRecomendacion(Contacto contacto, PublicacionDarEnAdopcion publicaciones){
        javaMailApi.enviarRemcomendacion(contacto.getTelefono(), publicaciones);
      }
    };
  public abstract void notificarDuenio(Duenio duenio, String titulo, String cuerpo);
  public abstract void enviarRecomendacion(Contacto contacto, PublicacionDarEnAdopcion publicaciones);
}
