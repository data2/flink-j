package com.data2.flink.j;

import com.data2.flink.j.order.file.FileOrderDO;
import com.data2.flink.j.order.file.OrderFileFilterFunction;
import com.data2.flink.j.order.file.OrderFileMapFuntion;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:40
 */
@SpringBootApplication
public class FlinkFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlinkFileApplication.class);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStreamSource = env.readTextFile("classpath:order.txt", CharsetNames.UTF_8);

        SingleOutputStreamOperator<FileOrderDO> transform = dataStreamSource.filter(new OrderFileFilterFunction()).map(new OrderFileMapFuntion());

        transform.getExecutionConfig().setParallelism(1);

        transform.addSink(new SinkFunction<FileOrderDO>() {
        });

    }
}
