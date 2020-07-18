package com.data2.flink.j;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author leewow
 * @description
 * @date 2020/7/18 下午6:37
 */
public class FlinkTransformer {
    public static class Filter implements FilterFunction<String> {

        @Override
        public boolean filter(String value) throws Exception {
            // 数据过滤
            return true;
        }
    }
    public static class Map implements MapFunction<String, Object> {

        @Override
        public Object map(String value) throws Exception {
            Object o = new Object();
            // 数据反序列化
            // 处理value
            return o;
        }
    }
}
