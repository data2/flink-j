package com.data2.flink.j;

import com.data2.flink.j.order.OrderDO;
import com.data2.flink.j.order.function.OrderFilterFunction;
import com.data2.flink.j.order.function.OrderMapFuntion;
import com.data2.flink.j.order.function.OrderSinkFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:40
 */
@SpringBootApplication
@Slf4j
public class FlinkFileApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FlinkFileApplication.class);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> dataStream = env.readTextFile("/Users/leewow/code/github/data2/flink-j/src/main/resources/order.txt", CharsetNames.UTF_8);

        SingleOutputStreamOperator<OrderDO> transform = dataStream.map(new OrderMapFuntion()).filter(new OrderFilterFunction());

//        transform.getExecutionConfig().setParallelism(1);

        transform.addSink(new OrderSinkFunction());

        env.execute("a job");

    }
}
