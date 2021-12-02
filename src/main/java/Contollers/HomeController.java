package Contollers;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class HomeController implements WithGlobalEntityManager, TransactionalOps {

    public static ModelAndView show(Request req, Response res){
        return new ModelAndView(null, "Homepage.html.hbs");
    }

}
