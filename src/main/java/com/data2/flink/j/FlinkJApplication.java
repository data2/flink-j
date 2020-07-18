package com.data2.flink.j;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @author leewow
 * @description
 * @date 2020/7/18 下午6:19
 */
@Slf4j
@SpringBootApplication
public class FlinkJApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlinkJApplication.class);

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", PropertyUtil.getProperty("kafka.servers"));
        properties.setProperty("zookeeper.connect", PropertyUtil.getProperty("kafka.zookeeper"));
        properties.setProperty("group.id", PropertyUtil.getProperty("kafka.group"));
        properties.setProperty("flink.partition-discovery.interval-millis", PropertyUtil.getProperty("kafka.partition-discovery.interval-millis"));

        // 数据源
        DataStream<String> text = env.addSource(
                new FlinkKafkaConsumer010(PropertyUtil.getProperty("kafka.topic"),
                        new SimpleStringSchema(), properties).setStartFromEarliest()
        ).name("FlinkKafkaConsumer010: FlinkJob");

        // 对数据源进行过滤
        DataStream<Object> transformedEvent =
                text.filter(new FlinkTransformer.Filter())
                        .map(new FlinkTransformer.Map());

        // 设置执行并行度
        transformedEvent.getExecutionConfig().setParallelism(1);
        // 设置数据持久的类
        transformedEvent.addSink(new FlinkSink());
        // execute program
        try {
            env.execute("FlinkJob");
        } catch (Exception e) {
            log.error("Error when execute FlinkJob", e);
        }
    }
}
