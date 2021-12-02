package Caracteristica;

import Duenio.PersistenceEntity;

import javax.persistence.Entity;

@Entity
public class BoolCaracteristica extends PersistenceEntity implements Caracteristica<Boolean> {

  String titulo;

  public BoolCaracteristica(String titulo) {
    this.titulo = titulo;
  }
  public BoolCaracteristica(){

  }

  public String getTitulo(){ return titulo; }
}
