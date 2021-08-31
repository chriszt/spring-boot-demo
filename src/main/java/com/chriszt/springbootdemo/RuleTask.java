package com.chriszt.springbootdemo;

import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

public class RuleTask {

    public void task(String filePath) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(1);
        DataStream<String> rawStream = env.readTextFile(filePath);
//        rawStream.print("rawStream");
        DataStream<User> userStream = rawStream.map(s -> {
            String[] tokens = s.split(",");
            return new User(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]));
        });
//        userStream.print("userStream");

        StreamTableEnvironment tabEnv = StreamTableEnvironment.create(env);
        Schema userSchema = Schema.newBuilder()
                .column("id", "INTEGER")
                .column("clazz", "STRING")
                .column("name", "STRING")
                .column("age", "INTEGER")
                .columnByExpression("inTime", "PROCTIME()")
                .build();
        tabEnv.createTemporaryView("UserTab", userStream, userSchema);

        tabEnv.executeSql(Rule.RULE1).print();
//        tabEnv.executeSql(FlinkSQL.RULE2).print();
        tabEnv.executeSql(Rule.RULE2).print();


        try {
            JobExecutionResult res = env.execute();
            System.out.println("NetRuntime: " + res.getNetRuntime() + "ms");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
