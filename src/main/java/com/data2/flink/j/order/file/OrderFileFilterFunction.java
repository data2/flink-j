package com.data2.flink.j.order.file;

import lombok.extern.slf4j.Slf4j;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:48
 */
@Slf4j
public class OrderFileFilterFunction implements org.apache.flink.api.common.functions.FilterFunction<FileOrderDO> {
    @Override
    public boolean filter(FileOrderDO fileOrderDO) {
        if (fileOrderDO != null && "1".equals(fileOrderDO.getOrderStatus())) {
            return true;
        }
        return false;
    }
}
