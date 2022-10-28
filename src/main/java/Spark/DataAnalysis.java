package Spark;

import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import com.google.common.collect.Iterators;
import org.apache.spark.api.java.JavaRDD;
import java.util.Iterator;
import scala.Tuple2;


public class DataAnalysis {
    public static void main(String[] args) {
        JavaSparkContext sc = new JavaSparkContext("local", "Data Analysis");
        JavaRDD<String> raw_data = sc.textFile("C:\\Users\\MSI\\Desktop\\Items\\Docs\\Data Engineering\\Projects\\data\\shootouts.csv");
        System.out.println("row number: " +raw_data.count());

        JavaRDD<DataModel> model_rdd = raw_data.map(new Function<String, DataModel>() {
            @Override
            public DataModel call(String line) throws Exception {
                String [] split = line.split(",");
                return new DataModel(split[0], split[1],
                        split[2], split[3]);
            }
        });

        JavaPairRDD<String, String> map_rdd = model_rdd.mapToPair(new PairFunction<DataModel, String, String>() {
            public Tuple2<String, String> call(DataModel dataModel) throws Exception {
                return new Tuple2<String, String>(dataModel.getWinner(), dataModel.getDate());
            }
        });

        JavaPairRDD<String, Iterable<String>> group_wins = map_rdd.groupByKey();
        JavaRDD<GroupedData> result_rdd = group_wins.map(new Function<Tuple2<String, Iterable<String>>, GroupedData>() {
            @Override
            public GroupedData call(Tuple2<String, Iterable<String>> stringIterableTuple2) throws Exception {
                Iterator<String> iterator_raw = stringIterableTuple2._2().iterator();
                return new GroupedData(stringIterableTuple2._1, Iterators.size(iterator_raw));
            }
        });

        result_rdd.foreach(new VoidFunction<GroupedData>() {
            @Override
            public void call(GroupedData groupedData) throws Exception {
                System.out.println(groupedData.getTeam() + " " + groupedData.getWin_count());
            }
        });
    }
}

