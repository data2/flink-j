package com.data2.flink.j.order.function;

import com.data2.flink.j.order.OrderDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

/**
 * @author data2
 * @description
 * @date 2020/12/11 下午5:23
 */
@Slf4j
public class OrderSinkFunction extends RichSinkFunction<OrderDO> {

    @Override
    public void close() {
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
    }

    @Override
    public void invoke(OrderDO value, Context context) {
        // 已支付未交付， 重发重试
        // 话费充值 ，重新调用上账接口
        if (value != null && "1".equals(value.getOrderStatus())){
            log.info("订单号：{}，状态：{}，重新上账", value.getOrderId(),value.getOrderStatus());
        }
    }
}

