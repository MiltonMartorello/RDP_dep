package Caracteristica;


import java.util.ArrayList;
import java.util.List;

public class RepositorioCaracteristica {
  private static RepositorioCaracteristica repoDeCaracteristicasInstance = new RepositorioCaracteristica();
  private List<TextCaracteristica> caracteristicas = new ArrayList<TextCaracteristica>();

  public static RepositorioCaracteristica getRepoDeCaracteristicasInstance() {
    return repoDeCaracteristicasInstance;
  }

  public RepositorioCaracteristica(){

  }
  public void agregarCaracteristica(TextCaracteristica caracteristica){
    caracteristicas.add(caracteristica);
  }
  public List<TextCaracteristica> getCaracteristicas(){
    return caracteristicas;
  }
}
