package com.chriszt.springbootdemo;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

public class WordCount {

    public void wordCount(String[] words) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> ds = env.fromElements(words);

        DataStream<Tuple2<String, Integer>> ds2 = ds.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (in, out) -> {
            String[] tokens = in.toLowerCase().split("\\.");
            for (String token : tokens) {
                out.collect(new Tuple2<String, Integer>(token, 1));
            }
        }).keyBy((KeySelector<Tuple2<String, Integer>, Object>) value -> {
//                System.out.println(value);
            return value.f0;
        }).sum(1);

        ds2.print();
        try {
            env.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
