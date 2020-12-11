package com.data2.flink.j.order.file;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author data2
 * @description
 * @date 2020/12/11 上午11:49
 */
public class OrderFileMapFuntion implements MapFunction<String, FileOrderDO> {
    @Override
    public FileOrderDO map(String s) {
        return JSON.parseObject(s, FileOrderDO.class);
    }
}
