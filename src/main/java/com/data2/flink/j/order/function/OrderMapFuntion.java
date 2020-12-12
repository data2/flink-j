package com.data2.flink.j.order.function;

import com.alibaba.fastjson.JSON;
import com.data2.flink.j.order.OrderDO;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:49
 */
public class OrderMapFuntion implements MapFunction<String, OrderDO> {
    @Override
    public OrderDO map(String s) {
        return JSON.parseObject(s, OrderDO.class);
    }
}
