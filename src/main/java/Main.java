/**
 * Created by Julian Dax on 30.09.15.
 */


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(Integer.parseInt(args[0]));
        post("/rescale", Main::RescaleRoute);
        post("/unrescale", Main::UnRescaleRoute);
        post("/standardize", Main::StandardizeRoute);
        post("/unstandardize", Main::UnStandardizeRoute);
    }

    private static Object RescaleRoute(Request request, Response response) throws JSONException {
        JSONArray values = new JSONArray(request.body());
        Stream<Double> valueStream = Utils.doubleStreamFromJsonArray(values);
        RescaleResult result = Math.rescale(valueStream.collect(Collectors.toList()));
        return JSONConverter.convertRescaleResultToJSON(result);
    }

    private static Object UnRescaleRoute(Request request, Response response) throws JSONException {
        RescaleResult result = JSONConverter.convertJSONToRescaleResult(new JSONObject(request.body()));
        List<Double> unrescaled = Math.unRescale(result);
        return new JSONArray(unrescaled);
    }

    private static Object StandardizeRoute(Request request, Response response) throws JSONException {
        List<Double> valueList = Utils.doubleStreamFromJsonArray(new JSONArray(request.body()))
                .collect(Collectors.toList());
        StandardizeResult standardizeResult = Math.standardize(valueList);

        return JSONConverter.convertStandardizeResultToJSON(standardizeResult);
    }

    private static Object UnStandardizeRoute(Request request, Response response) throws JSONException {
        StandardizeResult result = JSONConverter.convertJSONToStandardizeResult(new JSONObject(request.body()));
        return new JSONArray(Math.unStandardize(result));
    }

}


