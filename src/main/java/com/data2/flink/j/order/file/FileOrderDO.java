package com.data2.flink.j.order.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileOrderDO {
    private String orderId;

    /**
     * 0 未支付 未交付
     * 1 已支付 未交付
     * 2 已支付 已交付
     */
    private String orderStatus;


}
