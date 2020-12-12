package com.data2.flink.j.window.function;

import com.data2.flink.j.window.ItemViewCount;
import com.data2.flink.j.window.UserBehavior;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author data2
 * @description
 * @date 2020/12/12 上午11:34
 */
// in, out, key, window implements WindowFunction<Long, ItemViewCount, String, TimeWindow>
public class TopWindowFuntion extends RichWindowFunction<Long, ItemViewCount, Tuple, TimeWindow> {


    @Override
    public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<Long> iterable, Collector<ItemViewCount> collector) throws Exception {
        System.out.println();
        //collector.collect(new ItemViewCount(tuple, timeWindow.getEnd(), iterable.iterator().next()));
    }
}
