package repositoriosDB;

import Caracteristica.TextCaracteristica;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioCaracteristicas implements WithGlobalEntityManager {
  public static RepositorioCaracteristicas instancia = new RepositorioCaracteristicas();

  public void agregar(TextCaracteristica caracteristica) {
    entityManager().persist(caracteristica);
  }

  public List<TextCaracteristica> listar() {
    return entityManager().createQuery("from TextCaracteristica", TextCaracteristica.class)
        .getResultList();
  }

  public TextCaracteristica buscar(long id) {
    return entityManager().find(TextCaracteristica.class, id);
  }
}
