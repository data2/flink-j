package com.data2.flink.j.order.function;

import com.data2.flink.j.order.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:48
 */
@Slf4j
public class OrderFilterFunction implements FilterFunction<OrderDO> {
    @Override
    public boolean filter(OrderDO orderDO) {
        if (orderDO != null && "1".equals(orderDO.getOrderStatus())) {
            return true;
        }
        return false;
    }
}
