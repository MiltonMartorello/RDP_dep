package repositoriosDB;

import Rescatista.FormularioMascotaEncontradaSinChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioFormularioSinChapita implements WithGlobalEntityManager {
  public static RepositorioFormularioSinChapita instancia = new RepositorioFormularioSinChapita();

  public void agregar(FormularioMascotaEncontradaSinChapita formularioMascotaEncontradaSinChapita) {
    entityManager().persist(formularioMascotaEncontradaSinChapita);
  }

  public List<FormularioMascotaEncontradaSinChapita> listar() {
    return entityManager().createQuery("from FormularioMascotaEncontradaSinChapita", FormularioMascotaEncontradaSinChapita.class)
        .getResultList();
  }

  public FormularioMascotaEncontradaSinChapita buscar(long id) {
    return entityManager().find(FormularioMascotaEncontradaSinChapita.class, id);
  }
}
