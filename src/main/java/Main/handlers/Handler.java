package Main.handlers;

import DBMS.DBMS;
import Main.Answer;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nikitaborodulin on 30/10/15.
 */
public class Handler implements Route {

    public Object handle(Request request, Response response) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> m = request.params();

            String entity = request.queryParams("entity");
            String atr = request.queryParams("attribute");
            String search = request.queryParams("search");
            Map<String, String> map = new HashMap<>();
            map.put("entity", entity);
            map.put("atr", atr);
            map.put("search", search);
            Map<String, String> urlParams = Collections.unmodifiableMap(map);

            Answer answer = DBMS.search(entity, atr, search);


            //Answer answer = process(urlParams);
            response.status(answer.getCode());
            response.type("application/json");
            response.body(answer.getBody());
            return answer.getBody();
        } catch (Exception e) {
            response.status(400);
            response.body(e.getMessage());
            return e.getMessage();
        }

    }
}
