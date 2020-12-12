package com.data2.flink.j.window.function;

import com.data2.flink.j.window.UserBehavior;
import org.apache.flink.api.common.functions.AggregateFunction;

/**
 * @author data2
 * @description
 * @date 2020/12/12 上午11:34
 */
public class TopAggregateFunction implements AggregateFunction<UserBehavior, Long, Long> {


    @Override
    public Long createAccumulator() {
        return 0L;
    }

    @Override
    public Long add(UserBehavior userBehavior, Long aLong) {
        return aLong +1 ;
    }

    @Override
    public Long getResult(Long aLong) {
        return null;
    }


    @Override
    public Long merge(Long aLong, Long acc1) {
        return aLong + acc1;
    }
}
