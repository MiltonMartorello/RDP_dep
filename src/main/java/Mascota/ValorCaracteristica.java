package Mascota;


import Caracteristica.TextCaracteristica;
import Duenio.PersistenceEntity;

import javax.persistence.*;

@Entity
public class ValorCaracteristica extends PersistenceEntity {
  @Transient
  TextCaracteristica caracteristica;
  @Transient
  String valor;

  public ValorCaracteristica(TextCaracteristica caracteristica, String valor) {
    this.caracteristica = caracteristica;
    this.valor = valor;
  }
  public ValorCaracteristica(){

  }

  public TextCaracteristica getCaracteristica() {
    return caracteristica;
  }

  public String getValor() {
    return valor;
  }
}



