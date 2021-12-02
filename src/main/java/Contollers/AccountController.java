package Contollers;

import Duenio.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositoriosDB.RepositorioDuenios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.*;

public class AccountController implements WithGlobalEntityManager, TransactionalOps {

    public static ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "Login.html.hbs");
    }

    public Void login(Request req, Response res) {
        try {
            req.session().attribute("user_id",
                RepositorioDuenios.instancia.chequearUser(
                    req.queryParams("usuario"),
                    req.queryParams("password")
                )
            );
        } catch(NoSuchElementException e) {
            //TODO mandar a pagina de error
        }
        res.redirect("/");
        return null;
    }

    public static ModelAndView show_create(Request req, Response res){
        return new ModelAndView(null, "Create_account.html.hbs");
    }

    //public Duenio(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDeDocumento, String
    // numeroDeDocumento, List<Contacto> contactos, String user, String password, List<MedioDeComunicacionPreferido> medios)

    public Void create(Request req, Response res) {
        Contacto contacto = new Contacto(req.queryParams("nombre_contacto")
                , req.queryParams("apellido_contacto")
                , req.queryParams("telefono")
                , req.queryParams("email"));

        List<MedioDeComunicacion> medios;
        if (req.queryParams("SMS") != null && req.queryParams("EMAIL") != null) {
            MedioDeComunicacion sms = MedioDeComunicacion.SMS;
            MedioDeComunicacion email = MedioDeComunicacion.MAIL;
            medios = Arrays.asList(sms, email);
        } else if (req.queryParams("SMS") != null) {
            MedioDeComunicacion sms = MedioDeComunicacion.SMS;
            medios = Arrays.asList(sms);
        } else {
            MedioDeComunicacion email = MedioDeComunicacion.MAIL;
            medios = Arrays.asList(email);
        }

        Duenio duenio = new Duenio(req.queryParams("nombre")
                , req.queryParams("apellido")
                , LocalDate.parse(req.queryParams("nacimiento"))
                , TipoDocumento.valueOf(req.queryParams("tipo"))
                , req.queryParams("documento")
                , Arrays.asList(contacto)
                , req.queryParams("user")
                , req.queryParams("password")
                , medios);

        withTransaction(() -> RepositorioDuenios.instancia.agregar(duenio));

        res.redirect("/pet/new");
        //TODO chequear nombre de usuario no registrado
        // TODO validar contraseña??
        return null;
    }
}
