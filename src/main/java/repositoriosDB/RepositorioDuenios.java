package repositoriosDB;

import Duenio.Duenio;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioDuenios implements WithGlobalEntityManager {
  public static RepositorioDuenios instancia = new RepositorioDuenios();

  public void agregar(Duenio duenio) {
    entityManager().persist(duenio);
  }

  public List<Duenio> listar() {
    return entityManager().createQuery("from Duenio", Duenio.class)
        .getResultList();
  }

  public Duenio buscar(long id) {
    return entityManager().find(Duenio.class, id);
  }

  public Long chequearUser(String user, String password) {

    return RepositorioDuenios.instancia.listar().stream().filter(
        duenio ->
            duenio.getPassword().equals(password) &&
            duenio.getUser().equals(user)
    ).findAny().get().getId();
  } // TODO ver cohesion
}
