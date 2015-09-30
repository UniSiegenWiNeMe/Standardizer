import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julian Dax on 30.09.15.
 */
public class RescaleResult {
    public List<Double> results;
    public Double  max;
    public Double  min;

    public RescaleResult(List<Double> results, Double max, Double min) {
        this.results = results;
        this.max = max;
        this.min = min;
    }

    public RescaleResult() {
    }
}
