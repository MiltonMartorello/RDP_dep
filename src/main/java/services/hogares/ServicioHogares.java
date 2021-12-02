package services.hogares;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.hogares.entities.Hogar;
import services.hogares.entities.ResponseHogar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ServicioHogares {
    private static ServicioHogares instancia = null;
    //private static int maximaCantidadRegistrosDefault = 200;
    private static final String urlApi = "https://api.refugiosdds.com.ar/api/";
    private Retrofit retrofit;
//    private static String token = "Bearer o8I0oKS2QQhrvsQGj82x2ezxfM6YJWpnbcwL0NjaNi9UfLMJSqvxzB7aflCw";

    private ServicioHogares() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ServicioHogares instancia(){
        if(instancia== null){
            instancia = new ServicioHogares();
        }
        return instancia;
    }

    public List<Hogar> listadoDeHogares() throws  IOException {
        List<Hogar> hogares = new ArrayList<>();
        int pagina = 1;
        boolean hayMas;
        do {
            ResponseHogar request = paginaHogares(pagina);
            hogares.addAll(request.getHogares());
            pagina++;
            hayMas = parseInt(request.getTotal()) > hogares.size();
        }
        while(hayMas);
        return hogares;
    }

    public ResponseHogar paginaHogares(int pagina) throws  IOException {
        HogaresService hogaresService = this.retrofit.create(HogaresService.class);
        Call<ResponseHogar> requestHogares = hogaresService.hogares(pagina);
        Response<ResponseHogar> responseHogares = requestHogares.execute();
        return responseHogares.body();
    }
}