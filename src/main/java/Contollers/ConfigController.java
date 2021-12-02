package Contollers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import Caracteristica.TextCaracteristica;
import repositoriosDB.RepositorioCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConfigController implements WithGlobalEntityManager, TransactionalOps {
    public static ModelAndView show(Request req, Response res) {
        return new ModelAndView(null, "Config_pet_values.html.hbs");
    }

    public static ModelAndView listado(Request req, Response res){
        List<TextCaracteristica> caracteristicas = RepositorioCaracteristicas.instancia.listar();
        Map<String,Object> modelo = new HashMap<>();
        modelo.put("caracteristicas", caracteristicas);
        return new ModelAndView(modelo,"Config_pet_values.html.hbs");
    }

    public ModelAndView save_pet_value(Request req, Response res) {

        TextCaracteristica textCaracteristica =
                new TextCaracteristica(req.queryParams("nombre")
                );

        withTransaction(() -> {
            RepositorioCaracteristicas.instancia.agregar(textCaracteristica);
        });

        res.redirect("/admin/config");
        return null;
    }


}
