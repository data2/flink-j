package com.data2.flink.j.order.file;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:48
 */
public class OrderFileFilterFunction implements org.apache.flink.api.common.functions.FilterFunction<String> {
    @Override
    public boolean filter(String s) {
        return false;
    }
}
