package com.data2.flink.j;

import com.data2.flink.j.window.ItemViewCount;
import com.data2.flink.j.window.UserBehavior;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author data2
 * @description
 * @date 2020/12/11 下午9:42
 */
@Slf4j
@SpringBootApplication
public class WindowsApplication {
    public static void main(String[] args) throws Exception {

        SpringApplication.run(WindowsApplication.class);
        Configuration conf = new Configuration();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf);

        //实时数仓之热门商品统计-TopN

        // 模拟生产数据
        DataStreamSource<UserBehavior> dataStream = env.addSource(new RichSourceFunction<UserBehavior>() {
            @Override
            public void run(SourceContext<UserBehavior> sourceContext) throws Exception {
                log.info("run");
                Random random = new Random();
                List<Item> items = Lists.newArrayList(new Item("1001", "1", "苹果手机7"),
                        new Item("1002", "1", "苹果手机12"),
                        new Item("1003", "1", "MacBook"),
                        new Item("1004", "1", "AirPods"),
                        new Item("2001", "2", "小米手机"),
                        new Item("2002", "2", "小米平衡车"),
                        new Item("3001", "3", "华为手环"));

                while (true) {
                    Item item = items.get(random.nextInt(6));
                    String userId = String.valueOf(random.nextInt(10));
                    sourceContext.collect(new UserBehavior(userId, item.getItemId(), item.getItemDirectory(), "pv", new Date()));
                    Thread.sleep(2000);
                }
            }

            @Override
            public void cancel() {
            }
        });

//        dataStream.print();


        // 处理数据
        dataStream
                .filter(new FilterFunction<UserBehavior>() {
                    @Override
                    public boolean filter(UserBehavior userBehavior) throws Exception {
                        if (userBehavior != null && "pv".equals(userBehavior.getType())) {
                            return true;
                        }
                        return false;
                    }
                })
                .keyBy(userBehavior -> userBehavior.getItemId())
                .timeWindow(Time.seconds(10))
                .aggregate(new AggregateFunction<UserBehavior, Long, Long>() {

                    @Override
                    public Long createAccumulator() {
                        return 0L;
                    }

                    @Override
                    public Long add(UserBehavior userBehavior, Long aLong) {
                        return aLong + 1;
                    }

                    @Override
                    public Long getResult(Long aLong) {
                        return aLong;
                    }

                    @Override
                    public Long merge(Long aLong, Long acc1) {
                        return aLong + acc1;
                    }
                }, new WindowFunction<Long, ItemViewCount, String, TimeWindow>() {

                    @Override
                    public void apply(String aLong, TimeWindow timeWindow, Iterable<Long> iterable, Collector<ItemViewCount> collector) throws Exception {
                        collector.collect(new ItemViewCount(aLong, timeWindow.getEnd(), iterable.iterator().next()));
                    }
                })
                .keyBy(itemViewCount -> itemViewCount.getItemId())
                .print();// 每个商品在每个窗口的点击量的数据流


        // 启动
        env.execute("test");

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Item {
        private String itemId;
        private String itemDirectory;
        private String name;
    }

}
