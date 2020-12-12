package com.data2.flink.j.window;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author data2
 * @description
 * @date 2020/12/12 上午11:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemViewCount {
    private String itemId;
    private Long windowEnd;
    private Long count;
}
