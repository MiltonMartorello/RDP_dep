package services.hogares.entities;

import java.util.List;

public class ResponseHogar {
  private String total;
  private List<Hogar> hogares;

  public List<Hogar> getHogares() {
    return hogares;
  }

  public String getTotal() {
    return total;
  }

}
