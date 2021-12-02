package services.hogares;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import services.hogares.entities.ResponseHogar;

public interface HogaresService {
  @Headers({
      "accept: application/json",
      "Authorization: Bearer o8I0oKS2QQhrvsQGj82x2ezxfM6YJWpnbcwL0NjaNi9UfLMJSqvxzB7aflCw"
  })
  @GET("hogares")
  Call<ResponseHogar> hogares(@Query("offset") int offset);
}