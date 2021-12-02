package repositoriosDB;

import Mascota.Mascota;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioMascotas implements WithGlobalEntityManager {
  public static RepositorioMascotas instancia = new RepositorioMascotas();

  public void agregar(Mascota mascota) {
    entityManager().persist(mascota);
  }

  public List<Mascota> listar() {
    return entityManager().createQuery("from Mascota", Mascota.class)
        .getResultList();
  }

  public Mascota buscar(long id) {
    return entityManager().find(Mascota.class, id);
  }
}
