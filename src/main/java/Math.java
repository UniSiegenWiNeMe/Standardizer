import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Julian Dax on 30.09.15.
 */
public class Math {
    public static RescaleResult rescale(List<Double> values){
        double minValue = values.stream().min(Double::compare).get();
        double maxValue = values.stream().max(Double::compare).get();
        double difference = maxValue - minValue;
        Stream<Double> resultStream = values.parallelStream().map(x -> (x - minValue) / difference);
        List<Double> result =  resultStream.collect(Collectors.toList());
        return new RescaleResult(result, maxValue, minValue);
    }

    public static List<Double> unRescale(RescaleResult result){
        double diff = result.max - result.min;
        return result.results.parallelStream()
                .map((x) ->  x * diff + result.min)
                .collect(Collectors.toList());
    }

    public static StandardizeResult standardize(List<Double> values){
        DescriptiveStatistics statistics = new DescriptiveStatistics();
        values.forEach(statistics::addValue);
        double mean = statistics.getMean();
        double standardDerivation = statistics.getStandardDeviation();

        List<Double> results =  values.parallelStream().map((x) -> (x - mean) / standardDerivation)
                .collect(Collectors.toList());
        return new StandardizeResult(standardDerivation, mean, results);
    }

    public static List<Double> unStandardize(StandardizeResult standardizeResult){
        return standardizeResult.results.parallelStream()
                .map((x) -> x * standardizeResult.standardDeviation + standardizeResult.mean)
                .collect(Collectors.toList());
    }
}
