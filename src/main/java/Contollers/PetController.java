package Contollers;

import Asociacion.CentralAsociaciones;
import Caracteristica.TextCaracteristica;
import Duenio.Contacto;
import Duenio.TipoDocumento;
import Rescatista.FormularioMascotaEncontradaConChapita;
import Rescatista.FormularioMascotaEncontradaSinChapita;
import repositoriosDB.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import Mascota.Mascota;
import Mascota.Animal;
import Mascota.Sexo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PetController implements WithGlobalEntityManager, TransactionalOps{

    CentralAsociaciones centralAsociaciones = new CentralAsociaciones();

    public static ModelAndView show(Request req, Response res){
        List<TextCaracteristica> caracteristicas = RepositorioCaracteristicas.instancia.listar();
        Map<String,Object> modelo = new HashMap<>();
        modelo.put("caracteristicas", caracteristicas);
        return new ModelAndView(modelo, "Create_pet.html.hbs");
    }

    public static ModelAndView show_pets(Request req, Response res){
        List<Mascota> mascotas = RepositorioMascotas.instancia.listar();
        Map<String,Object> modelo = new HashMap<>();
        modelo.put("mascotas", mascotas);
        return new ModelAndView(modelo, "list_pets.html.hbs");
    }

    public ModelAndView create_pet(Request req, Response res) {
        // Esta funcion atrapa el chorro de  bytes del archivo binario
        InputStream datosArchivoAdjunto = getFotos(req);
        // lectura de todos los keys de los parametros q llegan
        System.out.println("Los parametros que llegaron son => "+req.queryParams());
        // como atrapar un value
        System.out.println("Nombre: "+req.queryParams("nombre"));
        System.out.println("Apodo: "+req.queryParams("apodo"));
        req.session().attribute("uid", 1);

        Mascota mascota = new Mascota(Arrays.asList()
                , Animal.valueOf(req.queryParams("especie"))
                , req.queryParams("nombre")
                , req.queryParams("apodo")
                , Integer.parseInt(req.queryParams("edad"))
                , Sexo.valueOf(req.queryParams("sexo"))
                , req.queryParams("descripcion")
                , Arrays.asList());

        withTransaction(() -> {
            RepositorioMascotas.instancia.agregar(mascota);
        });

        res.redirect("/pets");
        return null;
    }

    private static InputStream getFotos(Request req) {

        String location = "imagenes";
        long maxFileSize = 100000000;
        long maxRequestSize = 100000000;
        int fileSizeThreshold = 1024;
        req.attribute("org.eclipse.jetty.multipartConfig" , new MultipartConfigElement(
                location, maxFileSize, maxRequestSize, fileSizeThreshold));
        try {
            Part part = req.raw().getPart("image");
            System.out.println("leyendo archivo => "+req.raw().getPart("image").getSubmittedFileName());
            return part.getInputStream();
        } catch (IOException | ServletException e) {
            //Todo excepciones personalizadas
            e.printStackTrace();
        }

        return null;
    }

    public static ModelAndView found(Request req, Response res){
        return new ModelAndView(null, "Found_pet.html.hbs");
    }

    public ModelAndView found_pet(Request req, Response res) {

        Contacto contacto = new Contacto(req.queryParams("nombre_contacto")
                , req.queryParams("apellido_contacto")
                , req.queryParams("telefono")
                , req.queryParams("email"));

        FormularioMascotaEncontradaSinChapita formularioMascotaEncontradaSinChapita =
                new FormularioMascotaEncontradaSinChapita(req.queryParams("nombre")
                , req.queryParams("apellido")
                , LocalDate.parse(req.queryParams("nacimiento"))
                        , TipoDocumento.valueOf(req.queryParams("tipo"))
                , req.queryParams("documento")
                , req.queryParams("direccion")
                ,contacto
                );

        withTransaction(() -> {
            RepositorioFormularioSinChapita.instancia.agregar(formularioMascotaEncontradaSinChapita);
        });

        res.redirect("/");
        return null;
    }

    public static ModelAndView show_found_loaded_pet(Request req, Response res){
        return new ModelAndView(null, "Found_loaded_pet.html.hbs");
    }

    public ModelAndView found_loaded_pet(Request req, Response res) {

        Contacto contacto = new Contacto(req.queryParams("nombre_contacto")
                , req.queryParams("apellido_contacto")
                , req.queryParams("telefono")
                , req.queryParams("email"));

        FormularioMascotaEncontradaConChapita formularioMascotaEncontradaConChapita =
                new FormularioMascotaEncontradaConChapita(req.queryParams("nombre")
                        , req.queryParams("apellido")
                        , LocalDate.parse(req.queryParams("nacimiento"))
                        , TipoDocumento.valueOf(req.queryParams("tipo"))
                        , req.queryParams("documento")
                        , req.queryParams("direccion")
                        , null
                        ,contacto
                );

        withTransaction(() -> {
            RepositorioFormularioConChapita.instancia.agregar(formularioMascotaEncontradaConChapita);
        });

        res.redirect("/");
        return null;
    }
}

