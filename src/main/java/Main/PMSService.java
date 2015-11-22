package Main;

import Main.handlers.DB_Handler;
import Main.handlers.DeleteTupleHandler;
import Main.handlers.Handler;
import Main.handlers.additionHandler;
import com.beust.jcommander.JCommander;
import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;
import spark.Spark;

import java.util.logging.Logger;

import static spark.Spark.get;

/**
 * Created by nikitaborodulin on 28/10/15.
 */
public class PMSService {

    private static final Logger logger = Logger.getLogger(PMSService.class.getCanonicalName());

    public static void main(String[] args) {
        Spark.staticFileLocation("webapp");
        CommandLineOptions options = new CommandLineOptions();
        new JCommander(options, args);

        logger.finest("Options.debug = " + options.debug);
        logger.finest("Options.database = " + options.database);
        logger.finest("Options.dbHost = " + options.dbHost);
        logger.finest("Options.dbUsername = " + options.dbUsername);
        logger.finest("Options.dbPort = " + options.dbPort);
        logger.finest("Options.servicePort = " + options.servicePort);


        Sql2o sql2o = new Sql2o("jdbc:postgresql://" + options.dbHost + ":" + options.dbPort + "/" + options.database,
                options.dbUsername, options.dbPassword, new PostgresQuirks() {
        });

        get("/alive", new Handler());

        get("/survive", new additionHandler());

        get("/load_db", new DB_Handler());

        get("/deleteTuple", new DeleteTupleHandler());
    }
}
