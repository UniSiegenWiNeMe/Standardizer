import java.util.List;

/**
 * Created by Julian Dax on 30.09.15.
 */
public class StandardizeResult {
    public Double standardDeviation;
    public Double mean;
    public List<Double> results;

    public StandardizeResult(Double standardDeviation, Double mean, List<Double> results) {
        this.standardDeviation = standardDeviation;
        this.mean = mean;
        this.results = results;
    }

    public StandardizeResult() {
    }
}
