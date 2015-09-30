import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Julian Dax on 30.09.15.
 */
public class Utils {
    public static Stream<Double> doubleStreamFromJsonArray(JSONArray array){
        Stream.Builder<Double> streamBuilder = Stream.builder();
        for(int i = 0; i < array.length(); i++){
            try {
                streamBuilder.accept(array.getDouble(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return streamBuilder.build();
    }


}
