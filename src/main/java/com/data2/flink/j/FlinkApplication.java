package com.data2.flink.j;

import com.data2.flink.j.order.OrderDO;
import com.data2.flink.j.order.fun.OrderFilterFunction;
import com.data2.flink.j.order.fun.OrderMapFuntion;
import com.data2.flink.j.order.fun.OrderSinkFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.CharsetNames;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
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
public class FlinkApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(FlinkApplication.class);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStream = env.fromElements("{\"orderId\":\"1\",\"orderStatus\":\"0\"}", "{\"orderId\":\"2\",\"orderStatus\":\"1\"}\n");

        SingleOutputStreamOperator<OrderDO> transform = dataStream.map(new OrderMapFuntion()).filter(new OrderFilterFunction());

//        transform.getExecutionConfig().setParallelism(1);

        transform.addSink(new OrderSinkFunction());

        env.execute("a job");

    }
}
