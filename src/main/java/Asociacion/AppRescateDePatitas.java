package Asociacion;

import Contollers.AccountController;
import Contollers.HomeController;
import Contollers.ConfigController;
import Contollers.PetController;
import Duenio.Contacto;
import spark.Spark;
import spark.ResponseTransformer;
import static spark.debug.DebugScreen.enableDebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class AppRescateDePatitas {

  public static void main(String[] args){
    Spark.port(9000);

    CentralAsociaciones centralAsociaciones =  CentralAsociaciones.getInstance();
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    AccountController accountController = new AccountController();
    PetController petController = new PetController();
    ConfigController configController = new ConfigController();

    Spark.staticFiles.location("/public");
    enableDebugScreen();

    Spark.get("/", HomeController::show, engine);

    Spark.get("/account/login", AccountController::show, engine);

    Spark.post("/account/login", accountController::login);

    Spark.get("/account/create", AccountController::show_create, engine);

    //creacion del duenio que redirige a registrar a su mascosta
    Spark.post("/account/create", accountController::create);

    Spark.get("/pet/new", PetController::show, engine);

    Spark.get("/pets", PetController::show_pets, engine);

    Spark.post("/pet/create", petController::create_pet);

    Spark.get("/pet/found", PetController::found, engine);

    Spark.get("/pet/found_loaded", PetController::show_found_loaded_pet, engine);

    Spark.post("/pet/found_loaded", petController::found_loaded_pet);

    Spark.post("/pet/found", petController::found_pet);

    Spark.get("/admin/config", ConfigController::listado, engine);

    Spark.post("/admin/config", configController::save_pet_value);

    Recomendador recomendador = new Recomendador(centralAsociaciones);

    recomendador.generarRecomendacionSemanal();
  }
}
