package Caracteristica;

import Duenio.PersistenceEntity;

import javax.persistence.Entity;

@Entity
public class TextCaracteristica extends PersistenceEntity implements Caracteristica<String> {

  String titulo;

  public TextCaracteristica(String titulo) {
    this.titulo = titulo;
  }
  public TextCaracteristica(){

  }

  public String getTitulo(){
    return titulo;
  }
}