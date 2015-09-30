import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by brodo on 30.09.15.
 */
public class JSONConverter {
    public static JSONObject convertRescaleResultToJSON(RescaleResult rescaleResult){
        JSONObject rescaleResultJSON = new JSONObject();
        try {
            rescaleResultJSON.put("results",  new JSONArray(rescaleResult.results));
            rescaleResultJSON.put("min", rescaleResult.min);
            rescaleResultJSON.put("max", rescaleResult.max);
        } catch (JSONException e) {
            System.err.println("Could not create response JSON");
        }
        return rescaleResultJSON;
    }

    public static RescaleResult convertJSONToRescaleResult(JSONObject query){
        try {
            ArrayList<Double> results = Utils.doubleStreamFromJsonArray(query.getJSONArray("results"))
                    .collect(Collectors.toCollection(ArrayList::new));
            return new RescaleResult(results, query.getDouble("max"),query.getDouble("min"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new RescaleResult();
    }

    public static JSONObject convertStandardizeResultToJSON(StandardizeResult standardizeResult){
        JSONObject resultJSON = new JSONObject();
        try {
            resultJSON.put("standardDerivation", standardizeResult.standardDeviation);
            resultJSON.put("mean", standardizeResult.mean);
            resultJSON.put("results", new JSONArray(standardizeResult.results));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return resultJSON;
    }

    public static StandardizeResult convertJSONToStandardizeResult(JSONObject query){
        try {
            List<Double> results = Utils.doubleStreamFromJsonArray(query.getJSONArray("results"))
                    .collect(Collectors.toList());
            return new StandardizeResult(query.getDouble("standardDerivation"), query.getDouble("mean"),results);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new StandardizeResult();
    }

}
