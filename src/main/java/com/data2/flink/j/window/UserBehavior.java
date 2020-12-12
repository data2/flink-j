package com.data2.flink.j.window;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author data2
 * @description
 * @date 2020/12/12 上午10:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBehavior {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("商品id")
    private String itemId;
    @ApiModelProperty("商品目录id")
    private String itemDirectoryId;
    @ApiModelProperty("行为类型 eg:\"pv\", \"buy\", \"cart\", \"fav")
    private String type;
    @ApiModelProperty("")
    private Date date;

}
