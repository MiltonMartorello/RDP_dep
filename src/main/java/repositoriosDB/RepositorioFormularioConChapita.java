package repositoriosDB;

import Rescatista.FormularioMascotaEncontradaConChapita;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.List;

public class RepositorioFormularioConChapita implements WithGlobalEntityManager {
  public static RepositorioFormularioConChapita instancia = new RepositorioFormularioConChapita();

  public void agregar(FormularioMascotaEncontradaConChapita formularioMascotaEncontradaConChapita) {
    entityManager().persist(formularioMascotaEncontradaConChapita);
  }

  public List<FormularioMascotaEncontradaConChapita> listar() {
    return entityManager().createQuery("from FormularioMascotaEncontradaConChapita", FormularioMascotaEncontradaConChapita.class)
        .getResultList();
  }

  public FormularioMascotaEncontradaConChapita buscar(long id) {
    return entityManager().find(FormularioMascotaEncontradaConChapita.class, id);
  }
}
