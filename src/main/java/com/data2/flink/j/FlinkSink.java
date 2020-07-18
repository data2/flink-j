package com.data2.flink.j;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

/**
 * @author leewow
 * @description
 * @date 2020/7/18 下午6:38
 */
public class FlinkSink extends RichSinkFunction {
    private static final long serialVersionUID = 1L;

    @Override
    public void open(Configuration parameters) throws Exception {
        // 处理开始之前的操作
    }

    @Override
    public void close() throws Exception {}

    @Override
    public void invoke(Object obj, Context context) throws Exception {
        if (obj == null) {
            return;
        }
        // 具体对消息的处理
    }
}
